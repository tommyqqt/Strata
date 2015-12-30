/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.calc.runner;

import static com.opengamma.strata.collect.Guavate.toImmutableList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import com.opengamma.strata.calc.Column;
import com.opengamma.strata.calc.marketdata.CalculationEnvironment;
import com.opengamma.strata.calc.runner.function.result.ScenarioResult;
import com.opengamma.strata.collect.ArgChecker;
import com.opengamma.strata.collect.Messages;
import com.opengamma.strata.collect.result.Result;

/**
 * The default calculation runner.
 */
class DefaultCalculationRunner implements CalculationRunner {

  /**
   * Executes the tasks that perform the individual calculations.
   * This will typically be multi-threaded, but single or direct executors also work.
   */
  private final ExecutorService executor;
  /**
   * The calculation tasks that define the individual calculations.
   */
  private final CalculationTasks tasks;
  /**
   * The factory for consumers that wrap listeners to control threading and notify
   * them when calculations are complete.
   */
  private final ConsumerFactory consumerFactory = ListenerWrapper::new;

  /**
   * Creates an instance specifying the executor to use.
   * 
   * @param executor  executes the tasks that perform the calculations
   * @param tasks  the tasks that define the calculations
   */
  DefaultCalculationRunner(ExecutorService executor, CalculationTasks tasks) {
    this.executor = ArgChecker.notNull(executor, "executor");
    this.tasks = ArgChecker.notNull(tasks, "tasks");
  }

  //-------------------------------------------------------------------------
  @Override
  public CalculationTasks getTasks() {
    return tasks;
  }

  //-------------------------------------------------------------------------
  @Override
  public Results calculateSingleScenario(CalculationEnvironment marketData) {
    // perform the calculations
    Results results = calculateMultipleScenarios(marketData);

    // unwrap the results
    // since there is only one scenario it is not desirable to return scenario result containers
    List<Result<?>> unwrappedResults = results.getItems().stream()
        .map(DefaultCalculationRunner::unwrapScenarioResult)
        .collect(toImmutableList());

    return results.toBuilder().items(unwrappedResults).build();
  }

  /**
   * Unwraps the result from an instance of {@link ScenarioResult} containing a single result.
   * <p>
   * When the user executes a single scenario the functions are invoked with a set of scenario market data
   * of size 1. This means the functions are simpler and always deal with scenarios. But if the user has
   * asked for a single set of results they don't want to see a collection of size 1 so the scenario results
   * need to be unwrapped.
   * <p>
   * If {@code result} is a failure or doesn't contain a {@code ScenarioResult} it is returned.
   * <p>
   * If this method is called with a {@code ScenarioResult} containing more than one value it throws an exception.
   */
  private static Result<?> unwrapScenarioResult(Result<?> result) {
    if (result.isFailure()) {
      return result;
    }
    Object value = result.getValue();
    if (!(value instanceof ScenarioResult)) {
      return result;
    }
    ScenarioResult<?> scenarioResult = (ScenarioResult<?>) value;

    if (scenarioResult.size() != 1) {
      throw new IllegalArgumentException(Messages.format(
          "Expected one result but found {} in {}", scenarioResult.size(), scenarioResult));
    }
    return Result.success(scenarioResult.get(0));
  }

  @Override
  public Results calculateMultipleScenarios(CalculationEnvironment marketData) {
    Listener listener = new Listener(tasks.getColumns());
    calculateMultipleScenariosAsync(marketData, listener);
    return listener.result();
  }

  @Override
  public void calculateSingleScenarioAsync(CalculationEnvironment marketData, CalculationListener listener) {
    // the listener is decorated to unwrap ScenarioResults containing a single result
    UnwrappingListener unwrappingListener = new UnwrappingListener(listener);
    calculateMultipleScenariosAsync(marketData, unwrappingListener);
  }

  @Override
  public void calculateMultipleScenariosAsync(CalculationEnvironment marketData, CalculationListener listener) {
    List<CalculationTask> taskList = tasks.getTasks();
    Consumer<CalculationResult> consumer = consumerFactory.create(listener, taskList.size());
    taskList.stream().forEach(task -> runTask(task, marketData, consumer));
  }

  private void runTask(CalculationTask task, CalculationEnvironment marketData, Consumer<CalculationResult> consumer) {
    // submits a task to the executor to be run
    // the result of the task is passed to consumer.accept()
    CompletableFuture.supplyAsync(() -> task.execute(marketData), executor).thenAccept(consumer::accept);
  }

  //-------------------------------------------------------------------------
  /**
   * Calculation listener that receives the results of individual calculations and builds a set of {@link Results}.
   */
  private static final class Listener extends AggregatingCalculationListener<Results> {

    /** Comparator for sorting the results by row and then column. */
    private static final Comparator<CalculationResult> COMPARATOR =
        Comparator.comparingInt(CalculationResult::getRowIndex)
            .thenComparingInt(CalculationResult::getColumnIndex);

    /** List that is populated with the results as they arrive. */
    private final List<CalculationResult> results = new ArrayList<>();

    /** The columns that define what values are calculated. */
    private final List<Column> columns;

    private Listener(List<Column> columns) {
      this.columns = columns;
    }

    @Override
    public void resultReceived(CalculationResult result) {
      results.add(result);
    }

    @Override
    protected Results createAggregateResult() {
      results.sort(COMPARATOR);
      return buildResults(results, columns);
    }

    /**
     * Builds a set of results from the results of the individual calculations.
     *
     * @param calculationResults  the results of the individual calculations
     * @param columns  the columns that define what values are calculated
     * @return the results
     */
    private static Results buildResults(List<CalculationResult> calculationResults, List<Column> columns) {
      List<Result<?>> results =
          calculationResults.stream()
              .map(CalculationResult::getResult)
              .collect(toImmutableList());

      int columnCount = columns.size();
      int rowCount = (columnCount == 0) ? 0 : calculationResults.size() / columnCount;
      return Results.of(rowCount, columnCount, results);
    }
  }

  //-------------------------------------------------------------------------
  /**
   * Factory for consumers of calculation results.
   * <p>
   * The consumer wraps a {@link CalculationListener} to ensure it is only invoked
   * by a single thread at a time.
   * <p>
   * It is also responsible for keeping track of how many results have been received and invoking
   * {@link CalculationListener#calculationsComplete() calculationsComplete()}.
   */
  private interface ConsumerFactory {

    /**
     * Returns a consumer to deliver messages to the listener.
     *
     * @param listener  the listener to which the consumer will deliver messages
     * @param totalResultsCount  the total number of results expected
     * @return a consumer to deliver messages to the listener
     */
    public abstract Consumer<CalculationResult> create(CalculationListener listener, int totalResultsCount);
  }

  //-------------------------------------------------------------------------
  /**
   * Listener that decorates another listener and unwraps {@link ScenarioResult} instances
   * containing a single value before passing the value to the delegate listener.
   */
  private static final class UnwrappingListener implements CalculationListener {

    private final CalculationListener delegate;

    private UnwrappingListener(CalculationListener delegate) {
      this.delegate = delegate;
    }

    @Override
    public void resultReceived(CalculationResult calculationResult) {
      Result<?> result = calculationResult.getResult();
      Result<?> unwrappedResult = unwrapScenarioResult(result);
      CalculationResult unwrappedCalculationResult = calculationResult.toBuilder().result(unwrappedResult).build();
      delegate.resultReceived(unwrappedCalculationResult);
    }

    @Override
    public void calculationsComplete() {
      delegate.calculationsComplete();
    }
  }

}

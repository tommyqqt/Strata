/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.basics.index;

/**
 * Constants and implementations for standard Floating rate indices.
 * <p>
 * Each constant returns a standard definition of the specified index.
 */
final class FloatingRateNames {

  /**
   * Constant for GBP-LIBOR.
   */
  public static final FloatingRateName GBP_LIBOR = FloatingRateName.of("GBP-LIBOR");
  /**
   * Constant for USD-LIBOR.
   */
  public static final FloatingRateName USD_LIBOR = FloatingRateName.of("USD-LIBOR");
  /**
   * Constant for CHF-LIBOR.
   */
  public static final FloatingRateName CHF_LIBOR = FloatingRateName.of("CHF-LIBOR");
  /**
   * Constant for EUR-LIBOR.
   */
  public static final FloatingRateName EUR_LIBOR = FloatingRateName.of("EUR-LIBOR");
  /**
   * Constant for JPY-LIBOR.
   */
  public static final FloatingRateName JPY_LIBOR = FloatingRateName.of("JPY-LIBOR");
  /**
   * Constant for EUR-EURIBOR.
   */
  public static final FloatingRateName EUR_EURIBOR = FloatingRateName.of("EUR-EURIBOR");

  /**
   * Constant for GBP-SONIA Overnight index.
   */
  public static final FloatingRateName GBP_SONIA = FloatingRateName.of("GBP-SONIA");
  /**
   * Constant for USD-FED-FUND Overnight index.
   */
  public static final FloatingRateName USD_FED_FUND = FloatingRateName.of("USD-FED-FUND");
  /**
   * Constant for CHF-TOIS Overnight index.
   */
  public static final FloatingRateName CHF_TOIS = FloatingRateName.of("CHF-TOIS");
  /**
   * Constant for EUR-EONIA Overnight index.
   */
  public static final FloatingRateName EUR_EONIA = FloatingRateName.of("EUR-EONIA");
  /**
   * Constant for JPY-TONAR Overnight index.
   */
  public static final FloatingRateName JPY_TONAR = FloatingRateName.of("JPY-TONAR");

  /**
   * Constant for USD-FED-FUND Overnight index using averaging.
   */
  public static final FloatingRateName USD_FED_FUND_AVG = FloatingRateName.of("USD-FED-FUND-AVG");

  /**
   * Constant for FRC-EXT-CPI Price index.
   */
  public static final FloatingRateName FRC_EXT_CPI = FloatingRateName.of("FRC-EXT-CPI");
  /**
   * Constant for EUR-EXT-CPI Price index.
   */
  public static final FloatingRateName EUR_EXT_CPI = FloatingRateName.of("EUR-EXT-CPI");
  /**
   * Constant for GBP-RPI Price index.
   */
  public static final FloatingRateName GBP_RPI = FloatingRateName.of("GBP-RPI");
  /**
   * Constant for USA-CPI-U Price index.
   */
  public static final FloatingRateName USA_CPI_U = FloatingRateName.of("USA-CPI-U");

  //-------------------------------------------------------------------------
  /**
   * Restricted constructor.
   */
  private FloatingRateNames() {
  }

}

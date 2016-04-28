/**
 * Copyright (C) 2016 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.calc;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.currency.Currency;
import com.opengamma.strata.calc.config.Measure;
import com.opengamma.strata.calc.config.ReportingCurrency;

/**
 * Provides access to the column name and measure in the grid of results.
 * <p>
 * {@link CalculationRunner} provides the ability to calculate a grid of results
 * for a given set targets and columns. This defines the columns in the results.
 */
@BeanDefinition(builderScope = "private")
public final class ColumnHeader implements ImmutableBean {

  /**
   * The column name.
   * <p>
   * This is the name of the column, and should be unique in a list of columns.
   */
  @PropertyDefinition(validate = "notNull")
  private final ColumnName name;
  /**
   * The measure that was calculated.
   * <p>
   * This defines the calculation that was performed, such as 'PresentValue' or 'ParRate'.
   */
  @PropertyDefinition(validate = "notNull")
  private final Measure measure;
  /**
   * The currency of the result.
   * <p>
   * If the measure can be {@linkplain Measure#isCurrencyConvertible() automatically converted}
   * to a different currency, and a specific {@link ReportingCurrency} was specified,
   * then the currency will be stored here.
   * If the reporting currency is "natural", or the result has no currency, then this will be empty.
   */
  @PropertyDefinition(get = "optional")
  private final Currency currency;

  //-------------------------------------------------------------------------
  /**
   * Obtains an instance from the name and measure.
   *
   * @param name  the name
   * @param measure  the measure
   * @return a column with the specified measure
   */
  public static ColumnHeader of(ColumnName name, Measure measure) {
    return new ColumnHeader(name, measure, null);
  }

  /**
   * Obtains an instance from the name, measure and currency.
   *
   * @param name  the name
   * @param measure  the measure
   * @param currency  the currency
   * @return a column with the specified measure
   */
  public static ColumnHeader of(ColumnName name, Measure measure, Currency currency) {
    return new ColumnHeader(name, measure, currency);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ColumnHeader}.
   * @return the meta-bean, not null
   */
  public static ColumnHeader.Meta meta() {
    return ColumnHeader.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ColumnHeader.Meta.INSTANCE);
  }

  private ColumnHeader(
      ColumnName name,
      Measure measure,
      Currency currency) {
    JodaBeanUtils.notNull(name, "name");
    JodaBeanUtils.notNull(measure, "measure");
    this.name = name;
    this.measure = measure;
    this.currency = currency;
  }

  @Override
  public ColumnHeader.Meta metaBean() {
    return ColumnHeader.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the column name.
   * <p>
   * This is the name of the column, and should be unique in a list of columns.
   * @return the value of the property, not null
   */
  public ColumnName getName() {
    return name;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the measure that was calculated.
   * <p>
   * This defines the calculation that was performed, such as 'PresentValue' or 'ParRate'.
   * @return the value of the property, not null
   */
  public Measure getMeasure() {
    return measure;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency of the result.
   * <p>
   * If the measure can be {@linkplain Measure#isCurrencyConvertible() automatically converted}
   * to a different currency, and a specific {@link ReportingCurrency} was specified,
   * then the currency will be stored here.
   * If the reporting currency is "natural", or the result has no currency, then this will be empty.
   * @return the optional value of the property, not null
   */
  public Optional<Currency> getCurrency() {
    return Optional.ofNullable(currency);
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ColumnHeader other = (ColumnHeader) obj;
      return JodaBeanUtils.equal(name, other.name) &&
          JodaBeanUtils.equal(measure, other.measure) &&
          JodaBeanUtils.equal(currency, other.currency);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(name);
    hash = hash * 31 + JodaBeanUtils.hashCode(measure);
    hash = hash * 31 + JodaBeanUtils.hashCode(currency);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("ColumnHeader{");
    buf.append("name").append('=').append(name).append(',').append(' ');
    buf.append("measure").append('=').append(measure).append(',').append(' ');
    buf.append("currency").append('=').append(JodaBeanUtils.toString(currency));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ColumnHeader}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<ColumnName> name = DirectMetaProperty.ofImmutable(
        this, "name", ColumnHeader.class, ColumnName.class);
    /**
     * The meta-property for the {@code measure} property.
     */
    private final MetaProperty<Measure> measure = DirectMetaProperty.ofImmutable(
        this, "measure", ColumnHeader.class, Measure.class);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> currency = DirectMetaProperty.ofImmutable(
        this, "currency", ColumnHeader.class, Currency.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "name",
        "measure",
        "currency");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return name;
        case 938321246:  // measure
          return measure;
        case 575402001:  // currency
          return currency;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ColumnHeader> builder() {
      return new ColumnHeader.Builder();
    }

    @Override
    public Class<? extends ColumnHeader> beanType() {
      return ColumnHeader.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ColumnName> name() {
      return name;
    }

    /**
     * The meta-property for the {@code measure} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Measure> measure() {
      return measure;
    }

    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Currency> currency() {
      return currency;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return ((ColumnHeader) bean).getName();
        case 938321246:  // measure
          return ((ColumnHeader) bean).getMeasure();
        case 575402001:  // currency
          return ((ColumnHeader) bean).currency;
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code ColumnHeader}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<ColumnHeader> {

    private ColumnName name;
    private Measure measure;
    private Currency currency;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return name;
        case 938321246:  // measure
          return measure;
        case 575402001:  // currency
          return currency;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          this.name = (ColumnName) newValue;
          break;
        case 938321246:  // measure
          this.measure = (Measure) newValue;
          break;
        case 575402001:  // currency
          this.currency = (Currency) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public ColumnHeader build() {
      return new ColumnHeader(
          name,
          measure,
          currency);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("ColumnHeader.Builder{");
      buf.append("name").append('=').append(JodaBeanUtils.toString(name)).append(',').append(' ');
      buf.append("measure").append('=').append(JodaBeanUtils.toString(measure)).append(',').append(' ');
      buf.append("currency").append('=').append(JodaBeanUtils.toString(currency));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}

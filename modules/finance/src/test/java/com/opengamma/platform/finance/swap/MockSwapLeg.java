/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.platform.finance.swap;

import static com.opengamma.collect.TestHelper.date;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
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

import com.opengamma.basics.currency.Currency;
import com.opengamma.platform.finance.observation.FixedRateObservation;

/**
 * Mock.
 */
@BeanDefinition
public final class MockSwapLeg implements SwapLeg, ImmutableBean, Serializable {

  public static final SwapLeg MOCK_GBP1 = new MockSwapLeg(date(2012, 1, 15), date(2012, 8, 15), Currency.GBP);
  public static final ExpandedSwapLeg MOCK_EXPANDED_GBP1 = ExpandedSwapLeg.builder()
      .paymentPeriods(RatePaymentPeriod.builder()
          .paymentDate(date(2012, 8, 15))
          .accrualPeriods(RateAccrualPeriod.builder()
              .startDate(date(2012, 1, 15))
              .endDate(date(2012, 8, 15))
              .rateObservation(FixedRateObservation.of(0.012d))
              .build())
          .notional(1_000_000d)
          .currency(Currency.GBP)
          .build())
      .build();
  public static final SwapLeg MOCK_GBP2 = new MockSwapLeg(date(2012, 1, 15), date(2012, 6, 15), Currency.GBP);
  public static final SwapLeg MOCK_USD1 = new MockSwapLeg(date(2012, 1, 15), date(2012, 8, 15), Currency.USD);
  public static final ExpandedSwapLeg MOCK_EXPANDED_USD1 = ExpandedSwapLeg.builder()
      .paymentPeriods(RatePaymentPeriod.builder()
          .paymentDate(date(2012, 8, 15))
          .accrualPeriods(RateAccrualPeriod.builder()
              .startDate(date(2012, 1, 15))
              .endDate(date(2012, 8, 15))
              .rateObservation(FixedRateObservation.of(0.012d))
              .build())
          .notional(1_000_000d)
          .currency(Currency.USD)
          .build())
      .build();

  @PropertyDefinition(overrideGet = true)
  private final LocalDate startDate;
  @PropertyDefinition(overrideGet = true)
  private final LocalDate endDate;
  @PropertyDefinition(overrideGet = true)
  private final Currency currency;

  @Override
  public ExpandedSwapLeg expand() {
    if (this == MOCK_GBP1) {
      return MOCK_EXPANDED_GBP1;
    }
    if (this == MOCK_USD1) {
      return MOCK_EXPANDED_USD1;
    }
    throw new UnsupportedOperationException();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code MockSwapLeg}.
   * @return the meta-bean, not null
   */
  public static MockSwapLeg.Meta meta() {
    return MockSwapLeg.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(MockSwapLeg.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static MockSwapLeg.Builder builder() {
    return new MockSwapLeg.Builder();
  }

  private MockSwapLeg(
      LocalDate startDate,
      LocalDate endDate,
      Currency currency) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.currency = currency;
  }

  @Override
  public MockSwapLeg.Meta metaBean() {
    return MockSwapLeg.Meta.INSTANCE;
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
   * Gets the startDate.
   * @return the value of the property
   */
  @Override
  public LocalDate getStartDate() {
    return startDate;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the endDate.
   * @return the value of the property
   */
  @Override
  public LocalDate getEndDate() {
    return endDate;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency.
   * @return the value of the property
   */
  @Override
  public Currency getCurrency() {
    return currency;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      MockSwapLeg other = (MockSwapLeg) obj;
      return JodaBeanUtils.equal(getStartDate(), other.getStartDate()) &&
          JodaBeanUtils.equal(getEndDate(), other.getEndDate()) &&
          JodaBeanUtils.equal(getCurrency(), other.getCurrency());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getStartDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getEndDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getCurrency());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("MockSwapLeg{");
    buf.append("startDate").append('=').append(getStartDate()).append(',').append(' ');
    buf.append("endDate").append('=').append(getEndDate()).append(',').append(' ');
    buf.append("currency").append('=').append(JodaBeanUtils.toString(getCurrency()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code MockSwapLeg}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code startDate} property.
     */
    private final MetaProperty<LocalDate> startDate = DirectMetaProperty.ofImmutable(
        this, "startDate", MockSwapLeg.class, LocalDate.class);
    /**
     * The meta-property for the {@code endDate} property.
     */
    private final MetaProperty<LocalDate> endDate = DirectMetaProperty.ofImmutable(
        this, "endDate", MockSwapLeg.class, LocalDate.class);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> currency = DirectMetaProperty.ofImmutable(
        this, "currency", MockSwapLeg.class, Currency.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "startDate",
        "endDate",
        "currency");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -2129778896:  // startDate
          return startDate;
        case -1607727319:  // endDate
          return endDate;
        case 575402001:  // currency
          return currency;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public MockSwapLeg.Builder builder() {
      return new MockSwapLeg.Builder();
    }

    @Override
    public Class<? extends MockSwapLeg> beanType() {
      return MockSwapLeg.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code startDate} property.
     * @return the meta-property, not null
     */
    public MetaProperty<LocalDate> startDate() {
      return startDate;
    }

    /**
     * The meta-property for the {@code endDate} property.
     * @return the meta-property, not null
     */
    public MetaProperty<LocalDate> endDate() {
      return endDate;
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
        case -2129778896:  // startDate
          return ((MockSwapLeg) bean).getStartDate();
        case -1607727319:  // endDate
          return ((MockSwapLeg) bean).getEndDate();
        case 575402001:  // currency
          return ((MockSwapLeg) bean).getCurrency();
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
   * The bean-builder for {@code MockSwapLeg}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<MockSwapLeg> {

    private LocalDate startDate;
    private LocalDate endDate;
    private Currency currency;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(MockSwapLeg beanToCopy) {
      this.startDate = beanToCopy.getStartDate();
      this.endDate = beanToCopy.getEndDate();
      this.currency = beanToCopy.getCurrency();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -2129778896:  // startDate
          return startDate;
        case -1607727319:  // endDate
          return endDate;
        case 575402001:  // currency
          return currency;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -2129778896:  // startDate
          this.startDate = (LocalDate) newValue;
          break;
        case -1607727319:  // endDate
          this.endDate = (LocalDate) newValue;
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
    public MockSwapLeg build() {
      return new MockSwapLeg(
          startDate,
          endDate,
          currency);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code startDate} property in the builder.
     * @param startDate  the new value
     * @return this, for chaining, not null
     */
    public Builder startDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    /**
     * Sets the {@code endDate} property in the builder.
     * @param endDate  the new value
     * @return this, for chaining, not null
     */
    public Builder endDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    /**
     * Sets the {@code currency} property in the builder.
     * @param currency  the new value
     * @return this, for chaining, not null
     */
    public Builder currency(Currency currency) {
      this.currency = currency;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("MockSwapLeg.Builder{");
      buf.append("startDate").append('=').append(JodaBeanUtils.toString(startDate)).append(',').append(' ');
      buf.append("endDate").append('=').append(JodaBeanUtils.toString(endDate)).append(',').append(' ');
      buf.append("currency").append('=').append(JodaBeanUtils.toString(currency));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}

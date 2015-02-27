/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.platform.finance.fra;

import static com.opengamma.basics.BuySell.BUY;
import static com.opengamma.basics.date.BusinessDayConventions.MODIFIED_FOLLOWING;
import static com.opengamma.basics.date.HolidayCalendars.GBLO;
import static com.opengamma.basics.index.IborIndices.GBP_LIBOR_3M;
import static com.opengamma.collect.TestHelper.assertSerialization;
import static com.opengamma.collect.TestHelper.coverBeanEquals;
import static com.opengamma.collect.TestHelper.coverImmutableBean;
import static com.opengamma.collect.TestHelper.date;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.opengamma.basics.date.AdjustableDate;
import com.opengamma.basics.date.BusinessDayAdjustment;
import com.opengamma.basics.date.DaysAdjustment;
import com.opengamma.collect.id.StandardId;
import com.opengamma.platform.finance.TradeInfo;

/**
 * Test.
 */
@Test
public class FraTradeTest {

  private static final double NOTIONAL_1M = 1_000_000d;
  private static final double NOTIONAL_2M = 2_000_000d;
  private static final BusinessDayAdjustment BDA_MOD_FOLLOW = BusinessDayAdjustment.of(MODIFIED_FOLLOWING, GBLO);
  private static final DaysAdjustment MINUS_TWO_DAYS = DaysAdjustment.ofBusinessDays(-2, GBLO);
  private static final Fra FRA = Fra.builder()
      .buySell(BUY)
      .paymentDate(AdjustableDate.of(date(2015, 6, 16), BDA_MOD_FOLLOW))
      .startDate(date(2015, 6, 15))
      .endDate(date(2015, 9, 15))
      .fixedRate(0.25d)
      .index(GBP_LIBOR_3M)
      .fixingOffset(MINUS_TWO_DAYS)
      .notional(NOTIONAL_1M)
      .build();

  //-------------------------------------------------------------------------
  public void test_builder() {
    FraTrade test = FraTrade.builder()
        .standardId(StandardId.of("OG-Trade", "1"))
        .tradeInfo(TradeInfo.builder().tradeDate(date(2014, 12, 3)).build())
        .fra(FRA)
        .build();
    assertEquals(test.getStandardId(), StandardId.of("OG-Trade", "1"));
    assertEquals(test.getTradeInfo(), TradeInfo.builder().tradeDate(date(2014, 12, 3)).build());
    assertEquals(test.getFra(), FRA);
  }

  //-------------------------------------------------------------------------
  public void coverage() {
    FraTrade test = FraTrade.builder()
        .standardId(StandardId.of("OG-Trade", "1"))
        .tradeInfo(TradeInfo.builder().tradeDate(date(2014, 12, 3)).build())
        .fra(FRA)
        .build();
    coverImmutableBean(test);
    FraTrade test2 = FraTrade.builder()
        .standardId(StandardId.of("OG-Trade", "2"))
        .attributes(ImmutableMap.of("key", "value"))
        .tradeInfo(TradeInfo.builder().tradeDate(date(2014, 12, 5)).build())
        .fra(FRA.toBuilder().notional(NOTIONAL_2M).build())
        .build();
    coverBeanEquals(test, test2);
  }

  public void test_serialization() {
    FraTrade test = FraTrade.builder()
        .standardId(StandardId.of("OG-Trade", "1"))
        .tradeInfo(TradeInfo.builder().tradeDate(date(2014, 12, 3)).build())
        .fra(FRA)
        .build();
    assertSerialization(test);
  }

}

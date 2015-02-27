/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.platform.finance.fra;

import org.joda.beans.ImmutableBean;

import com.opengamma.platform.finance.Expandable;
import com.opengamma.platform.finance.Product;

/**
 * A product representing a forward rate agreement (FRA).
 * <p>
 * A FRA is a financial instrument that represents the one off exchange of a fixed
 * rate of interest for a floating rate at a future date.
 * <p>
 * For example, a FRA might involve an agreement to exchange the difference between
 * the fixed rate of 1% and the 'GBP-LIBOR-3M' rate in 2 months time.
 * <p>
 * Implementations must be immutable and thread-safe beans.
 */
public interface FraProduct
    extends Product, Expandable<ExpandedFra>, ImmutableBean {

}

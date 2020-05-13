package model.discount;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import model.discount.discountrule.DiscountRule;
import model.discount.discounttypes.pricediscount.PriceDiscount;

import java.math.BigDecimal;

/**
 * A model representing a discount that is applied to
 * a specific sale
 */

public interface Discount{
    void setTotalPriceReduction(double totalPriceReduction);
    Number getRequirement();
    DiscountRule getDiscountRule();
    BigDecimal getTotalPriceReduction();
    String getDate();
    boolean isDefault();
}

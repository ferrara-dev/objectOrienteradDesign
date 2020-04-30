package model.discount;


import model.discount.discountrule.DiscountRule;

/**
 * A model representing a discount that is applied to
 * a specific sale
 */
public interface Discount{
    void setTotalPriceReduction(double totalPriceReduction);
    Number getRequirement();
    DiscountRule getDiscountRule();
    Double getTotalPriceReduction();
    String getDate();
    boolean isDefault();
}

package model.discount;


import model.discount.discountrule.DiscountRule;

/**
 * A model representing a discount that is applied to
 * a specific sale
 */
public interface Discount{
    void setTotalPriceReduction(double totalPriceReduction);
    DiscountRule getDiscountPolicy();
    Double getTotalPriceReduction();
    String getDate();
    boolean isDefault();
}

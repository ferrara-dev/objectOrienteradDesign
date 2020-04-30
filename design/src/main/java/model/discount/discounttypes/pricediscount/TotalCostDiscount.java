package model.discount.discounttypes.pricediscount;

import model.discount.discountrule.pricediscountrule.PriceDiscountRule;

public class TotalCostDiscount implements PriceDiscount {
    private PriceDiscountRule priceDiscountRule;
    private double discountRate;
    private double totalPriceReduction;

    public TotalCostDiscount(PriceDiscountRule priceDiscountRule) {
        this.priceDiscountRule = priceDiscountRule;
        this.discountRate = priceDiscountRule.getDiscountRate();
    }


    @Override
    public void setTotalPriceReduction(double totalPriceReduction) {
        this.totalPriceReduction = totalPriceReduction;
    }

    /**
     * Get the minimum amount that needs to be spent to
     * get the discount.
     * @return
     */
    @Override
    public Double getRequirement() {
        return priceDiscountRule.getMinimumSpend();
    }

    /**
     * Get the rule that defines the discount
     * @return
     */
    @Override
    public PriceDiscountRule getDiscountRule() {
        return priceDiscountRule;
    }

    /**
     * Get the total price reduction
     * @return
     */
    @Override
    public Double getTotalPriceReduction() {
        return totalPriceReduction;
    }
    //TODO:
    @Override
    public String getDate() {
        return null;
    }

    /**
     * Check if the discount is a default discount or not
     * @return
     */
    @Override
    public boolean isDefault() {
        return false;
    }

    /**
     * Get the discount rate
     * @return
     */
    public double getDiscountRate() {
        return discountRate;
    }
}

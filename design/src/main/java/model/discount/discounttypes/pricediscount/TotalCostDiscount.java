package model.discount.discounttypes.pricediscount;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import model.discount.discountrule.pricediscountrule.PriceDiscountRule;

import java.math.BigDecimal;

public class TotalCostDiscount implements PriceDiscount {
    private PriceDiscountRule priceDiscountRule;
    private double discountRate;
    private BigDecimal totalPriceReduction = new BigDecimal("0");

    public TotalCostDiscount(PriceDiscountRule priceDiscountRule) {
        this.priceDiscountRule = priceDiscountRule;
        this.discountRate = priceDiscountRule.getDiscountRate();
    }

    public TotalCostDiscount() {
    }


    @Override
    public void setTotalPriceReduction(double totalPriceReduction) {
        this.totalPriceReduction = new BigDecimal(totalPriceReduction);
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
    public BigDecimal getTotalPriceReduction() {
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

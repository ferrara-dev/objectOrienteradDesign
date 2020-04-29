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

    @Override
    public PriceDiscountRule getDiscountPolicy() {
        return priceDiscountRule;
    }

    @Override
    public Double getTotalPriceReduction() {
        return totalPriceReduction;
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public boolean isDefault() {
        return false;
    }

    public double getDiscountRate() {
        return discountRate;
    }
}

package model.discount.discounttypes.defaultdiscount;

import model.discount.discountrule.pricediscountrule.PriceDiscountRule;
import model.discount.discounttypes.pricediscount.PriceDiscount;

/**
 * Class representing a default price discount
 */
public class NoPriceDiscount implements PriceDiscount {
    private double totalPriceReduction = 0;

    @Override
    public void setTotalPriceReduction(double totalPriceReduction) {
        this.totalPriceReduction = 0;
    }

    @Override
    public PriceDiscountRule getDiscountRule() {
        return null;
    }

    @Override
    public Double getRequirement() {
        return null;
    }

    @Override
    public Double getTotalPriceReduction() {
        return this.totalPriceReduction;
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public boolean isDefault() {
        return true;
    }
}

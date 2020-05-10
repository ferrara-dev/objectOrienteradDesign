package model.discount.discounttypes.defaultdiscount;

import model.discount.discountrule.pricediscountrule.PriceDiscountRule;
import model.discount.discounttypes.pricediscount.PriceDiscount;

import java.math.BigDecimal;

/**
 * Class representing a default price discount
 */
public class NoPriceDiscount implements PriceDiscount {
    private BigDecimal totalPriceReduction = new BigDecimal("0");

    @Override
    public void setTotalPriceReduction(double totalPriceReduction) {
        this.totalPriceReduction = new BigDecimal(totalPriceReduction);
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
    public BigDecimal getTotalPriceReduction() {
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

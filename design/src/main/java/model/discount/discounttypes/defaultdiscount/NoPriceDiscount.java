package model.discount.discounttypes.defaultdiscount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.discount.discountrule.pricediscountrule.PriceDiscountRule;
import model.discount.discounttypes.pricediscount.PriceDiscount;
import util.Calendar;

import java.math.BigDecimal;

/**
 * Class representing a default price discount
 */
public class NoPriceDiscount implements PriceDiscount {
    private BigDecimal totalPriceReduction = new BigDecimal("0");


    @JsonIgnore
    @Override
    public void setTotalPriceReduction(double totalPriceReduction) {
        this.totalPriceReduction = new BigDecimal(totalPriceReduction);
    }
    @JsonIgnore
    @Override
    public PriceDiscountRule getDiscountRule() {
        return null;
    }
    @JsonIgnore
    @Override
    public Double getRequirement() {
        return null;
    }
    @JsonIgnore
    @Override
    public BigDecimal getTotalPriceReduction() {
        return this.totalPriceReduction;
    }
    @JsonIgnore
    @Override
    public String getDate() {
        return null;
    }
    @JsonIgnore
    @Override
    public boolean isDefault() {
        return true;
    }
}

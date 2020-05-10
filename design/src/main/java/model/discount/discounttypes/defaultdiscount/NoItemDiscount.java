package model.discount.discounttypes.defaultdiscount;

import model.discount.discountrule.itemdiscountrule.ItemDiscountRule;
import model.discount.discounttypes.itemdiscount.ItemDiscount;

import java.math.BigDecimal;

/**
 * Class representing a default item discount
 */
public class NoItemDiscount implements ItemDiscount {
    private final BigDecimal totalPriceReduction = new BigDecimal("0");

    @Override
    public void setTotalPriceReduction(double totalPriceReduction) {

    }

    @Override
    public ItemDiscountRule getDiscountRule() {
        return null;
    }

    @Override
    public Integer getRequirement() {
        return null;
    }

    @Override
    public BigDecimal getTotalPriceReduction() {
        return totalPriceReduction;
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public boolean isDefault() {
        return true;
    }

    @Override
    public int getDiscountedItem() {
        return -1;
    }
}

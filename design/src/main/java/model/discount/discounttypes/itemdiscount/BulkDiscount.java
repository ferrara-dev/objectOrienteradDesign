package model.discount.discounttypes.itemdiscount;
import model.discount.discountrule.itemdiscountrule.ItemDiscountRule;

import java.math.BigDecimal;

/**
 * Class representing a bulk item discount
 */

public class BulkDiscount implements ItemDiscount {
    ItemDiscountRule discountRule;
    private BigDecimal totalPriceReduction = new BigDecimal("0");

    public BulkDiscount(ItemDiscountRule discountRule){
        this.discountRule = discountRule;
    }

    @Override
    public void setTotalPriceReduction(double totalPriceReduction) {
        this.totalPriceReduction = new BigDecimal(totalPriceReduction);
    }

    /**
     * Gets the minimum amount of items that needs to be
     * purchased to get the discount
     * @return
     */
    @Override
    public Integer getRequirement() {
        return discountRule.getMinimumAmountOfItems();
    }

    /**
     * Get the rule that defines the discount
     * @return
     */
    @Override
    public ItemDiscountRule getDiscountRule() {
        return discountRule;
    }
    /**
     * Get the total price reduction
     */
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
     * Get the discounted items id
     * @return
     */
    @Override
    public int getDiscountedItem() {
        return discountRule.getItemId();
    }
}

package model.discount.discounttypes.itemdiscount;
import model.discount.discountrule.itemdiscountrule.ItemDiscountRule;


public class BulkDiscount implements ItemDiscount {
    ItemDiscountRule discountRule;
    private double totalPriceReduction;

    public BulkDiscount(ItemDiscountRule discountRule){
        this.discountRule = discountRule;
    }

    @Override
    public void setTotalPriceReduction(double totalPriceReduction) {
        this.totalPriceReduction = totalPriceReduction;
    }

    @Override
    public ItemDiscountRule getDiscountPolicy() {
        return discountRule;
    }

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


    @Override
    public int getDiscountedItem() {
        return discountRule.getItemId();
    }
}

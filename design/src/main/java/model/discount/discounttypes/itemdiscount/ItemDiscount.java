package model.discount.discounttypes.itemdiscount;
import model.discount.Discount;
import model.discount.discountrule.itemdiscountrule.ItemDiscountRule;

public interface ItemDiscount extends Discount {
    int getDiscountedItem();

    @Override
    ItemDiscountRule getDiscountRule();
    @Override
    public Integer getRequirement();
}

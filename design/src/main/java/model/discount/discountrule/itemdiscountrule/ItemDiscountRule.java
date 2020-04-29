package model.discount.discountrule.itemdiscountrule;

import model.discount.discountrule.DiscountRule;

public interface ItemDiscountRule extends DiscountRule {
    int getMinimumAmountOfItems();
    int getItemId();

    @Override
    boolean checkRequierments(double purchasedAmountOfItems);
}

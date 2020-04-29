package model.discount.discountrule.pricediscountrule;

import model.discount.discountrule.DiscountRule;

public interface PriceDiscountRule extends DiscountRule {
    double getMinimumSpend();

    @Override
    boolean checkRequierments(double amountSpent);
}

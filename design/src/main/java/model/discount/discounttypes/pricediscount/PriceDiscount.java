package model.discount.discounttypes.pricediscount;

import model.discount.Discount;
import model.discount.discountrule.pricediscountrule.PriceDiscountRule;

public interface PriceDiscount extends Discount {
    @Override
    PriceDiscountRule getDiscountRule();
    @Override
    public Double getRequirement();
}

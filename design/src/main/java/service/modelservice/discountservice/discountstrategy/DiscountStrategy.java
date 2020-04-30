package service.modelservice.discountservice.discountstrategy;

import model.discount.discountrule.DiscountRule;

import java.util.ArrayList;

public interface DiscountStrategy {
    String BULK_DISCOUNT = "Bulk Discount";
    String TOTAL_PRICE_DISCOUNT = "Price Discount";

    //boolean applyStrategy(DiscountDTO discountDTOs);
    DiscountRule[] getRules();
}

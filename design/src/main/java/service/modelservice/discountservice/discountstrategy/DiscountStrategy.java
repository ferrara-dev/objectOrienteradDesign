package service.modelservice.discountservice.discountstrategy;
import util.datatransferobject.DiscountDTO;
import model.discount.discountrule.DiscountRule;

import java.util.ArrayList;

public interface DiscountStrategy {
    String BULK_DISCOUNT = "Bulk Discount";
    String PRICE_DISCOUNT = "Price Discount";

    boolean applyStrategy(DiscountDTO discountDTOs);
    ArrayList<DiscountRule> getRules();
}

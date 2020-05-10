package model.discount.discountrule;

import java.util.List;

public interface DiscountRule {
    String ITEM_DISCOUNT = "Item discount";
    String PRICE_DISCOUNT = "Price discount";
    String getDiscountClass();
    List<String> getAvailable();
    String getDescription();
    boolean checkRequierments(double obj);
    double getRequierment();
    double getDiscountRate();
}

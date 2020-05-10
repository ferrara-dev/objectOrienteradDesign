package service.modelservice.discountservice.discountstrategy;

import util.datatransferobject.DiscountDTO;

import java.util.ArrayList;

public interface DiscountStrategy <rule>{
    String BULK_DISCOUNT = "Bulk Discount";
    String TOTAL_PRICE_DISCOUNT = "Price Discount";

    ArrayList<rule> applyStrategy(DiscountDTO discountDTO);
}

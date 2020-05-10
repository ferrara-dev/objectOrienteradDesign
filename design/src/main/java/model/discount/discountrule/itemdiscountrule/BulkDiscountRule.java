package model.discount.discountrule.itemdiscountrule;

import java.util.List;

public class BulkDiscountRule implements ItemDiscountRule {
    private String discountClass = "Item discount";
    private final double reduction;
    private final int minimumAmountOfItems;
    private final int limit;
    private final int itemId;

    public BulkDiscountRule(int itemId, int minimumAmountOfItems, int limit, double reduction) {
        this.itemId = itemId;
        this.minimumAmountOfItems = minimumAmountOfItems;
        this.limit = limit;
        this.reduction = reduction;
    }

    public double getReduction() {
        return reduction;
    }


    public int getLimit() {
        return limit;
    }

    @Override
    public int getMinimumAmountOfItems() {
        return minimumAmountOfItems;
    }

    @Override
    public int getItemId() {
        return itemId;
    }

    @Override
    public String getDiscountClass() {
        return discountClass;
    }

    @Override
    public List<String> getAvailable() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean checkRequierments(double purchasedAmountOfItems) {
        if (this.minimumAmountOfItems <= purchasedAmountOfItems)
            return true;
        return false;
    }

    @Override
    public double getRequierment() {
        return minimumAmountOfItems;
    }

    @Override
    public double getDiscountRate() {
        return reduction;
    }

    public String getDiscountType() {
        return null;
    }

}

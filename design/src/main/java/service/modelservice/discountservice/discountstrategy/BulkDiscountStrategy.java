package service.modelservice.discountservice.discountstrategy;


import util.datatransferobject.DiscountDTO;
import model.discount.discountrule.itemdiscountrule.BulkDiscountRule;

import java.util.ArrayList;


public class BulkDiscountStrategy implements DiscountStrategy {
    private ArrayList<BulkDiscountRule> bulkDiscountRules;

    public BulkDiscountStrategy() {
        this.bulkDiscountRules = new ArrayList<>();
    }

    @Override
    public boolean applyStrategy(DiscountDTO discountDTO) {
        int itemIdCounter = 0;
        String[] discountItemId = discountDTO.getItemId().split(":");
        int[] itemId = new int[discountItemId.length];
        for (int i = 0; i < itemId.length; i++)
            itemId[i] = Integer.parseInt(discountItemId[i]);

        while (itemIdCounter < itemId.length) {
            int minimumAmountOfItems = Integer.parseInt(discountDTO.getRequirement());
            int limit = Integer.parseInt(discountDTO.getLimit());
            double priceReduction = Double.parseDouble(discountDTO.getReduction());
            BulkDiscountRule bulkDiscountRule = new BulkDiscountRule(itemId[itemIdCounter++], minimumAmountOfItems, limit, priceReduction);
            bulkDiscountRules.add(bulkDiscountRule);
        }
        return bulkDiscountWasAdded();
    }

    @Override
    public ArrayList getRules() {
        return bulkDiscountRules;
    }

    private boolean bulkDiscountWasAdded() {
        return !bulkDiscountRules.isEmpty();
    }

}

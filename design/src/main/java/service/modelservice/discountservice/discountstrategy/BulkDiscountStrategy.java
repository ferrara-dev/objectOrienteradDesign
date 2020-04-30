package service.modelservice.discountservice.discountstrategy;


import util.datatransferobject.DiscountDTO;
import model.discount.discountrule.itemdiscountrule.BulkDiscountRule;

import java.util.ArrayList;


public class BulkDiscountStrategy implements DiscountStrategy {
    private ArrayList<BulkDiscountRule> bulkDiscountRules;

    /**
     * A concrete implementation of a discount strategy, applies
     * a strategy to get a bulk discount on on ore more items.
     * @param discountDTO Information about the discount that
     * the strategy is applied to.
     */
    public BulkDiscountStrategy(DiscountDTO discountDTO) {
        this.bulkDiscountRules = new ArrayList<>();
        applyStrategy(discountDTO);
    }

    private boolean applyStrategy(DiscountDTO discountDTO) {
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
    public BulkDiscountRule [] getRules() {
        return bulkDiscountRules.toArray(new BulkDiscountRule[0]);
    }

    private boolean bulkDiscountWasAdded() {
        return !bulkDiscountRules.isEmpty();
    }

}

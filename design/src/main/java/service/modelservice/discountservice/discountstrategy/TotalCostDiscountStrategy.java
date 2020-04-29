package service.modelservice.discountservice.discountstrategy;

import util.datatransferobject.DiscountDTO;
import model.discount.discountrule.pricediscountrule.TotalCostDiscountRule;


import java.util.ArrayList;

public class TotalCostDiscountStrategy implements DiscountStrategy {
    private ArrayList<TotalCostDiscountRule> totalCostDiscountRules;

    public TotalCostDiscountStrategy(){
        totalCostDiscountRules = new ArrayList<>();
    }

    @Override
    public boolean applyStrategy(DiscountDTO discountDTO) {
        double minimumSpent = Double.parseDouble(discountDTO.getRequirement());
        double priceReduction = Double.parseDouble(discountDTO.getReduction());
                TotalCostDiscountRule priceDiscountPolicy = new TotalCostDiscountRule(minimumSpent,priceReduction);
                totalCostDiscountRules.add(priceDiscountPolicy);
        return priceDiscountWasAdded();
    }


    @Override
    public ArrayList getRules() {
        return totalCostDiscountRules;
    }


    private boolean priceDiscountWasAdded() {
        return !totalCostDiscountRules.isEmpty();
    }
}

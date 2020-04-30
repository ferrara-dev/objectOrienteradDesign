package service.modelservice.discountservice.discountstrategy;

import model.discount.discountrule.DiscountRule;
import util.datatransferobject.DiscountDTO;
import model.discount.discountrule.pricediscountrule.TotalCostDiscountRule;


import java.util.ArrayList;

public class TotalCostDiscountStrategy implements DiscountStrategy{
    private ArrayList<TotalCostDiscountRule> totalCostDiscountRules;

    public TotalCostDiscountStrategy(DiscountDTO discountDTO){
        totalCostDiscountRules = new ArrayList<>();
        applyStrategy(discountDTO);
    }

    private boolean applyStrategy(DiscountDTO discountDTO) {
        double minimumSpent = Double.parseDouble(discountDTO.getRequirement());
        double priceReduction = Double.parseDouble(discountDTO.getReduction());
                TotalCostDiscountRule priceDiscountPolicy = new TotalCostDiscountRule(minimumSpent,priceReduction);
                totalCostDiscountRules.add(priceDiscountPolicy);
        return priceDiscountWasAdded();
    }


    @Override
    public TotalCostDiscountRule [] getRules() {
        return totalCostDiscountRules.toArray(new TotalCostDiscountRule[0]);
    }


    private boolean priceDiscountWasAdded() {
        return !totalCostDiscountRules.isEmpty();
    }
}

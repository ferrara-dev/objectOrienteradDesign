package service.modelservice.discountservice.discountstrategy;
import util.datatransferobject.DiscountDTO;
import model.discount.discountrule.pricediscountrule.TotalCostDiscountRule;


import java.util.ArrayList;

public class TotalCostDiscountStrategy implements DiscountStrategy<TotalCostDiscountRule>{
    /**
     * A concrete implementation of a discount strategy, applies
     * a strategy to get a discount on the total cost
     * of the sale.
     */
    public TotalCostDiscountStrategy( ){
    }

    public ArrayList<TotalCostDiscountRule> applyStrategy(DiscountDTO discountDTO) {
        ArrayList<TotalCostDiscountRule> totalCostDiscountRules = new ArrayList<>();
        double minimumSpent = Double.parseDouble(discountDTO.getRequirement());
        double priceReduction = Double.parseDouble(discountDTO.getReduction());
                TotalCostDiscountRule priceDiscountPolicy = new TotalCostDiscountRule(minimumSpent,priceReduction);
                totalCostDiscountRules.add(priceDiscountPolicy);
        return totalCostDiscountRules;
    }

}

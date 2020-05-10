package model.discount.discountrule.pricediscountrule;


import java.util.List;

public class TotalCostDiscountRule implements PriceDiscountRule {
    private final String discountClass = "Price discount";
    private double minimumSpent;
    private double priceReductionRate;

    public TotalCostDiscountRule(double minimumSpent, double priceReductionRate){
        this.minimumSpent = minimumSpent;
        this.priceReductionRate = priceReductionRate;
    }

    public double getDiscountRate() {
        return priceReductionRate;
    }

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
    public boolean checkRequierments(double amountSpent) {
        if(this.minimumSpent <= amountSpent)
            return true;
        return false;
    }

    @Override
    public double getRequierment() {
        return minimumSpent;
    }

    @Override
    public double getMinimumSpend() {
        return minimumSpent;
    }


}

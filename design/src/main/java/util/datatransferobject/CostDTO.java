package util.datatransferobject;

import model.sale.saleinformation.cost.CostDetail;

/**
 * Data Transfer Object used to update the view about changes in the
 * <code> Cost </code> model.
 */
public class CostDTO implements DataTransferObject{
    private final double finalCost;
    private final double runningTotal;
    private final double totalVAT;
    private final double priceDiscount;
    private final double totalPriceOfDiscount;
    public CostDTO(double finalCost, double toPay, double totalVAT, double priceDiscount, double totalPriceOfDiscount){
        this.finalCost = finalCost;
        this.runningTotal = toPay;
        this.totalVAT = totalVAT;
        this.priceDiscount = priceDiscount;
        this.totalPriceOfDiscount = totalPriceOfDiscount;
    }
    public CostDTO(CostDetail costDetail){
        this.finalCost = costDetail.getFinalCost().getNumber().doubleValue();
        this.runningTotal = costDetail.getRunningTotal().getNumber().doubleValue();
        this.totalVAT = costDetail.getTotalVAT().getNumber().doubleValue();
        this.priceDiscount = costDetail.getPriceDiscount().getTotalPriceReduction().doubleValue();
        this.totalPriceOfDiscount = costDetail.getTotalDiscountOfPrice().doubleValue();
    }

    public double getPriceDiscount() {
        return priceDiscount;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public double getRunningTotal() {
        return runningTotal;
    }

    public double getTotalPriceOfDiscount() {
        return totalPriceOfDiscount;
    }

    public double getTotalVAT() {
        return totalVAT;
    }
}

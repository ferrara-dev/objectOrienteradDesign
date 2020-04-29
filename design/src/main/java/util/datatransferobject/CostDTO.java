package util.datatransferobject;

/**
 * Data Transfer Object used to update the view about changes in the
 * <code> Cost </code> model.
 */
public class CostDTO {
    private final double totalCost;
    private final double toPay;
    private final double totalVAT;
    private final double priceDiscount;

    public CostDTO(double totalCost, double toPay, double totalVAT, double priceDiscount){
        this.totalCost = totalCost;
        this.toPay = toPay;
        this.totalVAT = totalVAT;
        this.priceDiscount = priceDiscount;
    }

    public double getPriceDiscount() {
        return priceDiscount;
    }

    public double getToPay() {
        return toPay;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getTotalVAT() {
        return totalVAT;
    }
}

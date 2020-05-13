package util.datatransferobject;

import model.amount.RunningTotal;
import model.sale.SaleItem;
import model.sale.saleinformation.cost.CostDetail;
import model.sale.saleinformation.SaleTransaction;

import java.util.List;

public class SaleTransactionDTO {
    private RunningTotal runningTotal;
    private CostDetail costDetail;
    private List<SaleItem> saleItems;
    private String saleState;
    private String saleID;

    public SaleTransactionDTO(SaleTransaction saleTransaction){
        costDetail = saleTransaction.getCost();
        saleItems = saleTransaction.getCart().getItems();
        saleState = saleTransaction.getSaleState().getCurrentState().name();
        saleID = saleTransaction.getSaleSpecification().getSaleId().getValue();
    }

    public SaleTransactionDTO(){

    }

    public void setCostDetail(CostDetail costDetail) {
        this.costDetail = costDetail;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public String getSaleState() {
        return saleState;
    }

    public void setSaleState(String saleState) {
        this.saleState = saleState;
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public CostDetail getCostDetail() {
        return costDetail;
    }
}

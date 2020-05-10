package model.sale.saleinformation.cost;

import model.amount.Change;
import model.amount.FinalCost;
import model.amount.RunningTotal;
import model.amount.TotalVAT;

public class PaymentDetails {
    private RunningTotal runningTotal;
    private TotalVAT totalVAT;
    private FinalCost finalCost;
    private Change change;

    public Change getChange() {
        return change;
    }

    public FinalCost getFinalCost() {
        return finalCost;
    }

    public RunningTotal getRunningTotal() {
        return runningTotal;
    }

    public TotalVAT getTotalVAT() {
        return totalVAT;
    }

    public void setChange(Change change) {
        this.change = change;
    }

    public void setTotalVAT(TotalVAT totalVAT) {
        this.totalVAT = totalVAT;
    }

    public void setFinalCost(FinalCost finalCost) {
        this.finalCost = finalCost;
    }

    public void setRunningTotal(RunningTotal runningTotal) {
        this.runningTotal = runningTotal;
    }

}

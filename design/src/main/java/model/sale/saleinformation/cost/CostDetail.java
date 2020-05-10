package model.sale.saleinformation.cost;
import model.ObservableModel;
import model.amount.Change;
import model.amount.FinalCost;
import model.amount.RunningTotal;
import model.amount.TotalVAT;
import model.discount.discounttypes.defaultdiscount.NoPriceDiscount;
import model.discount.discounttypes.pricediscount.PriceDiscount;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
import model.Element;
import service.visitor.Visitor;
import util.exception.notfoundexception.NotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;


public class CostDetail implements ObservableModel, Element {
    private PriceDiscount priceDiscount;
    private ArrayList<EventObserver> eventObservers = new ArrayList<>();
    private RunningTotal runningTotal;
    private TotalVAT totalVAT;
    private FinalCost finalCost;
    private Change change;
    private BigDecimal totalDiscountOfPrice = new BigDecimal("0");

    public CostDetail (){
        priceDiscount = new NoPriceDiscount();
        runningTotal = new RunningTotal();
        totalVAT = new TotalVAT();
        finalCost = new FinalCost();
    }

    public void createDefault(){
        priceDiscount = new NoPriceDiscount();
        runningTotal = new RunningTotal();
        totalVAT = new TotalVAT();
        finalCost = new FinalCost();
        change = new Change();
    }

    public void setChange(Change change) {
        this.change = change;
    }

    public Change getChange() {
        return change;
    }

    public void setTotalDiscountOfPrice(BigDecimal totalDiscountOfPrice) {
        this.totalDiscountOfPrice = totalDiscountOfPrice;
    }

    public void increaseTotalDiscountOfPrice(BigDecimal totalDiscountOfPrice){
        this.totalDiscountOfPrice = this.totalDiscountOfPrice.add(totalDiscountOfPrice);
    }

    public BigDecimal getTotalDiscountOfPrice() {
        return totalDiscountOfPrice;
    }

    public void setPriceDiscount(PriceDiscount priceDiscount) {
        notifyObservers(new PropertyChangeEvent("priceDiscount",priceDiscount, this.priceDiscount));
        this.priceDiscount = priceDiscount;
    }

    public PriceDiscount getPriceDiscount() {
        return priceDiscount;
    }

    public void updateRunningTotal(BigDecimal bigDecimal){
        notifyObservers(new PropertyChangeEvent("runningTotal",runningTotal, this.runningTotal));
        runningTotal.increaseValue(bigDecimal);
    }

    public void updateTotalVAT(BigDecimal bigDecimal){
        notifyObservers(new PropertyChangeEvent("totalVAT",totalVAT, this.totalVAT));
        totalVAT.increaseValue(bigDecimal);
    }

    public RunningTotal getRunningTotal() {
        return runningTotal;
    }

    public void setRunningTotal(RunningTotal runningTotal) {
        notifyObservers(new PropertyChangeEvent("runningTotal",runningTotal, this.runningTotal));
        this.runningTotal = runningTotal;
    }

    public FinalCost getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(FinalCost finalCost) {
        notifyObservers(new PropertyChangeEvent("finalCost",finalCost, this.finalCost));
        this.finalCost = finalCost;
    }

    public TotalVAT getTotalVAT() {
        return totalVAT;
    }

    public void setTotalVAT(TotalVAT totalVAT) {
        notifyObservers(new PropertyChangeEvent("totalVAT",totalVAT, this.totalVAT));
        this.totalVAT = totalVAT;
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {
        for(EventObserver eventObserver: eventObservers)
            eventObserver.newEvent(observedEvent);
    }

    @Override
    public void addObserver(EventObserver eventObserver) {
        eventObservers.add(eventObserver);
    }

    @Override
    public void removeObserver(EventObserver eventObserver) {

    }

    @Override
    public void acceptVisitor(Visitor visitor) throws NotFoundException {
        visitor.processElement(this);
    }
}

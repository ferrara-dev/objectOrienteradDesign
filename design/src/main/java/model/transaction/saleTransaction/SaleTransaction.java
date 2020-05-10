package model.transaction.saleTransaction;

import model.ObservableModel;
import model.sale.saleinformation.cost.CostDetail;
import model.sale.saleinformation.ProductCart;
import model.sale.saleinformation.SaleSpecification;
import model.sale.saleinformation.salestate.SaleState;
import observer.EventObserver;
import observer.ObservedEvent;
import model.Element;
import service.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class SaleTransaction implements ObservableModel, Element {
    private List<EventObserver> observers;
    private SaleSpecification saleSpecification;

    public SaleTransaction(SaleSpecification sale){
        this.saleSpecification = sale;
        observers = new ArrayList<>();
    }

    public SaleSpecification getSaleSpecification() {
        return saleSpecification;
    }

    public SaleState getSaleState() {
        return saleSpecification.getSaleState();
    }

    public CostDetail getCost() {
        return saleSpecification.getCost();
    }

    public ProductCart getCart() {
        return saleSpecification.getCart();
    }

    public void setObservers(ArrayList<EventObserver> observers) {
        this.observers = observers;
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {

    }

    @Override
    public void addObserver(EventObserver eventObserver) {

    }

    @Override
    public void removeObserver(EventObserver eventObserver) {

    }

    @Override
    public void acceptVisitor(Visitor visitor) {

    }
}

package model.sale.saleinformation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.ObservableModel;
import model.sale.saleinformation.cost.CostDetail;
import model.sale.saleinformation.ProductCart;
import model.sale.saleinformation.SaleSpecification;
import model.sale.saleinformation.salestate.SaleState;
import observer.modelobserver.EventObserver;
import observer.modelobserver.ObservedEvent;
import model.Element;
import service.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class SaleTransaction {

    private SaleSpecification saleSpecification;

    public SaleTransaction(SaleSpecification sale){
        this.saleSpecification = sale;
    }
    public SaleTransaction(){

    }
    public SaleSpecification getSaleSpecification() {
        return saleSpecification;
    }
    @JsonIgnore
    public SaleState getSaleState() {
        return saleSpecification.getSaleState();
    }
    @JsonIgnore
    public CostDetail getCost() {
        return saleSpecification.getCost();
    }
    @JsonIgnore
    public ProductCart getCart() {
        return saleSpecification.getCart();
    }

    public void setSaleSpecification(SaleSpecification saleSpecification) {
        this.saleSpecification = saleSpecification;
    }

    public void removeObservers(){
        saleSpecification.getCost().setEventObservers(null);
        saleSpecification.getCart().setEventObservers(null);
        saleSpecification.getCost().setEventObservers(null);
    }

}

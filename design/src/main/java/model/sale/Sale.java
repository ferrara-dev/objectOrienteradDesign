package model.sale;

import model.ObservableModel;
import model.amount.Cost;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;

import java.util.ArrayList;

/**
 * Class representing a sale
 */

public class Sale implements ObservableModel {
    private boolean discounted;
    private SaleDetail saleDetail;
    private Cart cart;
    private Cost cost;
    private double runningTotal = 0;
    private double cashBack = 0;
    private ArrayList<EventObserver> eventObservers = new ArrayList<>();

    /**
     * Creates a new instance representing details about
     * a specific transaction.
     */
    public Sale() {

    }

    /**
     * Check if the sale is discounted or not
     * @return
     */
    public boolean isDiscounted() {
        return discounted;
    }

    /**
     * Get change from after the purchase
     * @return
     */
    public double getCashBack() {
        return cashBack;
    }

    /**
     * Set the change
     * @param cashBack
     */
    public void setCashBack(double cashBack) {
        notifyObservers(new PropertyChangeEvent("cashBack",cashBack,this.cashBack));
        this.cashBack = cashBack;
    }

    public Cost getCost() {
        return cost;
    }

    /**
     * Update the sale cost
     */
    public void updateCost() {
        cost.updateCost(this);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * Start the sale and initiate
     * fields to default values
     */
    public void startSale(){
        this.cart = new Cart();
        this.cost = new Cost();
        this.saleDetail = new SaleDetail();
    }
    /**
     * Initiate fields in the saleDetail to default values
     */
    public void createDefault(){
        saleDetail.createDefault();
    }
    /**
     * inactivate the sale by setting the boolean field <code> active </code>
     * in <code> SaleDetail </code> as false
     */
    public void finishSale(){
        saleDetail.setActive(false);
    }

    /**
     *
     * Method used to initiate fields to default values in a newly started sale
     */
    public SaleDetail getSaleDetail() {
        return saleDetail;
    }

    public void setRunningTotal(double runningTotal) {
        this.runningTotal = runningTotal;
    }

    public double getRunningTotal() {
        return runningTotal;
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {
        for(EventObserver eventObserver: eventObservers)
            eventObserver.stateHasChanged(observedEvent);
    }

    @Override
    public void addObserver(EventObserver eventObserver) {
        eventObservers.add(eventObserver);
    }

    @Override
    public void removeObserver(EventObserver eventObserver) {

    }
}

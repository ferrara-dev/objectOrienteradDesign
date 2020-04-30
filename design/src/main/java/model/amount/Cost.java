package model.amount;

import util.datatransferobject.CostDTO;
import model.ObservableModel;
import model.discount.discounttypes.defaultdiscount.NoPriceDiscount;
import model.discount.discounttypes.pricediscount.PriceDiscount;
import model.sale.Sale;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.StateChangeEvent;
import util.exception.IllegalDiscountCombinationException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Amount class representing a cost
 */
public class Cost implements ObservableModel, Amount  {
    private PriceDiscount priceDiscount;
    private ArrayList<EventObserver> eventObservers = new ArrayList<>();
    double toPay = 0;
    private double totalCost = 0;
    private double totalVAT = 0;
    private final String type;

    public Cost(){
        this.type = MONETARY;
        priceDiscount = new NoPriceDiscount();
    }

    /**
     * Update the cost, iterates through every
     * present <code> SaleItem </code> included
     * in <code> items </code> and recalculates
     * total price and VAT.
     *
     * fires <code> StateChangeEvent </code>
     * to update the view about changes in the
     * model.
     * @param sale
     */
    public void updateCost(Sale sale){
        totalCost = 0;
        totalVAT = 0;
        for(int i = 0; i < sale.getCart().size(); i++) {
            totalCost += sale.getCart().get(i).getTotalPrice() - sale.getCart().get(i).getItemDiscount().getTotalPriceReduction();
            totalVAT += sale.getCart().get(i).getTotalVAT();
        }

        if(Objects.nonNull(priceDiscount)){
                totalCost = totalCost - priceDiscount.getTotalPriceReduction();
            }
        setNumber(totalCost);
    }

    /**
     * Getter, gets the <code> totalCost </code> attribute
     * @return the total cost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Getter, gets the sales total VAT
     * @return the total VAT
     */
    public double getTotalVAT() {
        return totalVAT;
    }

    /**
     * Setter, sets <code> priceDiscount </code> if
     * it has not already been set.
     * @param priceDiscount the discount that is applied
     */
    public void setPriceDiscount(PriceDiscount priceDiscount){
        if(this.priceDiscount.isDefault())
            if(Objects.nonNull(priceDiscount))
            {
                this.priceDiscount = priceDiscount;
            }
        else
            throw new IllegalDiscountCombinationException("Only one price discount can be applied per sale");
    }

    /**
     * Notifies registered observers about a change in the model
     * @param observedEvent
     */
    @Override
    public void notifyObservers(ObservedEvent observedEvent) {
        for(EventObserver observer: eventObservers){
            observer.stateHasChanged(observedEvent);
        }
    }

    /**
     * Adds an observer to the model
     * @param eventObserver
     */
    @Override
    public void addObserver(EventObserver eventObserver) {
        eventObservers.add(eventObserver);
    }

    /**
     * //TODO : Implement functionality to remove an observer
     *          from the model.
     * @param eventObserver
     */
    @Override
    public void removeObserver(EventObserver eventObserver) {

    }

    /**
     * Override to compare if a monetary amount is
     * sufficient for paying the cost.
     * @param amount
     * @return
     */
    @Override
    public boolean compare(Amount amount) {
        if (amount == null)
            return false;

        if (amount.getType().equals(this.type))
            if(amount.getNumber().doubleValue()>= toPay)
                return true;

        return false;
    }

    /**
     * Override to get type of amount
     * @return
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Override to get the value of the amount
     */
    @Override
    public Double getNumber() {
        return toPay;
    }

    @Override
    public Double subtract(Amount amount) {
        toPay = toPay - amount.getNumber().doubleValue();
        return toPay - amount.getNumber().doubleValue();
    }

    /**
     * Override to set the value of the amount
     * @param number
     */
    @Override
    public void setNumber(Number number) {
        this.toPay = number.doubleValue();
        notifyObservers(new StateChangeEvent(new CostDTO(this.totalCost,this.toPay,this.totalVAT,this.priceDiscount.getTotalPriceReduction())));
    }
}

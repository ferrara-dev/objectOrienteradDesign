package model.sale;

import model.ObservableModel;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
import util.Calendar;

import java.util.ArrayList;

public class SaleDetail implements ObservableModel {
    private boolean active;
    private boolean completed;
    private Date dateOfSale;
    private SaleId saleId;
    private ArrayList<EventObserver> eventObservers = new ArrayList<>();

    /**
     * Creates a new instance representing details about
     * a specific transaction.
     */
    public SaleDetail() {

    }

    public void createDefault() {
        setSaleId(new SaleId());
        setCompleted(false);
        setActive(true);
        setDateOfSale(Calendar.getDate());
    }


    /**
     * Setter, sets the unique saleId
     * @param id
     */
    public void setSaleId(SaleId id) {
        this.saleId = id;
    }

    /**
     * Getter, gets the <code> saleId </code> field
     * each <code> SaleId </code> has a unique value
     * @return
     */
    public SaleId getSaleId() {
        return saleId;
    }

    /**
     * Getter, gets the date that the sale was conducted
     * @return
     */
    public Date getDateOfSale() {
        return this.dateOfSale;
    }

    public boolean isActive() {
        return active;
    }

    /**
     * Set the boolean field <code> active </code>.
     * the sale is set as active when the sale is started.
     *
     * When the sale is completed, and payed for,
     * <code> active </code> is set false.
     *
     * fires a <code> PropertyChangeEvent </code> to update the
     * view about the state change in the model.
     */
    public void setActive(boolean active) {
        notifyObservers(new PropertyChangeEvent("active", active, this.active));
        this.active = active;
    }

    /**
     * Check if the sale is completed
     *
     * @return
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * set the sale as completed.
     * The sale is set as completed when the
     * sale is ended
     *
     * A <code> PropertyChangeEvent </code> is
     * fired to update the view about the state
     * change in the model.
     */
    public void setCompleted(boolean completed) {
        notifyObservers(new PropertyChangeEvent("completed", completed, this.completed));
        this.completed = completed;
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {
        for (EventObserver eventObserver : eventObservers) {
            eventObserver.stateHasChanged(observedEvent);
        }
    }

    @Override
    public void addObserver(EventObserver eventObserver) {
        eventObservers.add(eventObserver);
    }

    @Override
    public void removeObserver(EventObserver eventObserver) {
        eventObservers.remove(eventObserver);
    }


    /**
     * Setter, sets the date that the sale is conducted
     *
     * The <code> dateOfSale </code> field is set at the
     * initialization of the sale when the method
     * <code> createSaleDefault(); </code> is called
     * @param dateOfSale
     */
    private void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }
}

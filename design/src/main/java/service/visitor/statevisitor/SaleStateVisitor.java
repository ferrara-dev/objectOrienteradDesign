package service.visitor.statevisitor;

import factory.IntegrationFactory;
import integration.DataBaseHandler;
import model.amount.FinalCost;
import model.sale.saleinformation.cost.CostDetail;
import model.sale.saleinformation.salestate.SaleState;
import model.sale.saleinformation.salestate.State;
import model.sale.saleinformation.SaleTransaction;
import observer.modelobserver.EventObserver;
import observer.modelobserver.ObservedEvent;
import observer.modelobserver.PropertyChangeEvent;
import observer.modelobserver.StateChange;
import service.visitor.Visitor;
import service.visitor.cartvisitor.ProductCartVisitor;
import util.datatransferobject.CostDTO;

import java.util.ArrayList;


public class SaleStateVisitor implements Visitor<SaleState, SaleTransaction> {
    private SaleTransaction saleTransaction;
    private static SaleStateVisitor instance;
    private ArrayList<EventObserver> eventObservers;

    private SaleStateVisitor() {
        eventObservers = new ArrayList<>();
    }

    /**
     * Singleton method used to create an instance of the class
     * and make sure that multiple instances can not be created
     * <code> synchronized </code> keyword is used to make the
     * calls to the method thread safe.
     * * @return
     */
    public static Visitor<SaleState, SaleTransaction> getInstance() {
        if (instance == null) {
            synchronized (ProductCartVisitor.class) {
                if (instance == null) {
                    instance = new SaleStateVisitor();
                }
            }
        }
        return instance;
    }

    @Override
    public void setData(SaleTransaction saleSpecification) {
        this.saleTransaction = saleSpecification;
    }

    @Override
    public void processElement(SaleState saleState) {
        saleState.nextState();
        if (saleState.getCurrentState().equals(State.SALE_PAYMENT_STATE))
            setFinalCost();

        else if (saleState.getCurrentState().equals(State.SALE_COMPLETE_STATE))
            finalizeSale();
        saleState.notifyObservers(new StateChange(saleState.getCurrentState()));
    }

    private void finalizeSale() {
        saleTransaction.removeObservers();
        DataBaseHandler dataBaseHandler = IntegrationFactory.SALE_LOG.getDataBaseHandler();
        dataBaseHandler.register(saleTransaction.getSaleSpecification().getSaleId().getValue(), saleTransaction);
    }

    private void setFinalCost() {
        CostDetail costDetail = saleTransaction.getCost();
        FinalCost finalCost = new FinalCost();
        finalCost.setNumber(costDetail.getRunningTotal().getNumber());
        saleTransaction.getCost().setFinalCost(finalCost);
        saleTransaction.getCost().notifyObservers(new PropertyChangeEvent("costDetail", new CostDTO(costDetail), null));
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {
        eventObservers.forEach(eventObserver -> {
            eventObserver.newEvent(observedEvent);
        });
    }

    @Override
    public void addObserver(EventObserver eventObserver) {
        eventObservers.add(eventObserver);
    }

    @Override
    public void removeObserver(EventObserver eventObserver) {

    }
}

package service;


import factory.builderpattern.SaleSpecificationFactory;
import model.sale.saleinformation.SaleSpecification;
import model.sale.saleinformation.SaleTransaction;
import observer.modelobserver.EventObserver;
import service.visitor.Visitor;
import factory.VisitorFactory;


import java.util.ArrayList;
/**
 * Service class responsible for initiating and processing the state of a
 * <code> SaleTransaction </code> object.
 *
 * The operations are performed using implementations of the <code> Visitor </code>
 * interface.
 */
public class SaleService {
    private ArrayList<EventObserver> eventObservers;
    SaleTransaction saleTransaction;
    Visitor visitor;

    public SaleService() {

    }

    /**
     * Start a new sale on request by the <code> SaleController </code>
     * A new instance of <code> SaleTransaction </code> is created by
     * calling a enum class providing implementations of the <code> Factory </code>
     * interface.
     */
    public void startSale() {
        SaleSpecification saleSpecification = SaleSpecificationFactory.DEFAULT_SALE.create(eventObservers);
        saleTransaction = new SaleTransaction(saleSpecification);
        visitor = VisitorFactory.SALE_STATE_VISITOR.getVisitor();
        visitor.setData(saleTransaction);
        saleTransaction.getSaleState().acceptVisitor(visitor);
    }

    /**
     *  Get the information about the current <code>SaleTransaction</code>
     * @return the current sale transaction
     */
    public SaleTransaction getSaleInformation() {
        return this.saleTransaction;
    }

    /**
     * End the sale by setting its state as completed.
     */
    public void endSale() {
        visitor = VisitorFactory.SALE_STATE_VISITOR.getVisitor();
        visitor.setData(saleTransaction);
        saleTransaction.getSaleSpecification().getSaleState().acceptVisitor(visitor);
    }

    public void setEventObservers(ArrayList<EventObserver> eventObservers) {
        this.eventObservers = eventObservers;
    }
}

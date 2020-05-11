package service;


import factory.builderpattern.SaleSpecificationFactory;
import model.physicalobjects.Register;
import model.sale.saleinformation.SaleSpecification;
import model.transaction.saleTransaction.SaleTransaction;
import observer.EventObserver;
import service.handlerpattern.exceptionhandler.ExceptionHandlingFactory;
import service.visitor.Visitor;
import factory.VisitorFactory;
import startup.layer.ServiceCreator;
import util.exception.DataBaseAccessFailureException;
import util.exception.notfoundexception.NotFoundException;

import java.util.ArrayList;


/**
 * Service class that performs business logic and manipulates the sale model
 */
public class SaleService {
    private ArrayList<EventObserver> eventObservers;
    SaleTransaction saleTransaction;
    Visitor visitor;

    public SaleService(ServiceCreator serviceCreator) {

    }

    public SaleService() {

    }

    public void setUp(){

    }

    /**
     * Start a new sale on request by the <code> SaleController </code>
     * A new instance of <code> SaleTransaction </code> is created by
     * calling a enum class providing implementations of the <code> Factory </code>
     * interface.
     */
    public void startSale(){
        SaleSpecification saleSpecification = SaleSpecificationFactory.DEFAULT_SALE.create(eventObservers);
        saleTransaction = new SaleTransaction(saleSpecification);
        visitor = VisitorFactory.SALE_STATE_VISITOR.getVisitor();
        visitor.setData(saleSpecification);
        saleTransaction.getSaleState().acceptVisitor(visitor);
    }

    public SaleTransaction getSaleInformation() {
        return this.saleTransaction;
    }

    /**
     * End the sale by setting its state as completed
     */
    public void endSale(){
        visitor = VisitorFactory.SALE_STATE_VISITOR.getVisitor();
        visitor.setData(saleTransaction.getSaleSpecification());
        try {
            saleTransaction.getSaleSpecification().getSaleState().acceptVisitor(visitor);
        }catch (NotFoundException | DataBaseAccessFailureException e){
            ExceptionHandlingFactory.getExceptionHandlingChain().handle(e);
        }
    }

    public void setEventObservers(ArrayList<EventObserver> eventObservers) {

        this.eventObservers = eventObservers;
    }
}
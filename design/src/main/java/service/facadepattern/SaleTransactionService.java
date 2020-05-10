package service.facadepattern;

import factory.builderpattern.SaleSpecificationFactory;
import model.sale.saleinformation.SaleSpecification;
import model.transaction.saleTransaction.SaleTransaction;
import observer.EventObserver;
import startup.layer.ServiceCreator;
import util.datatransferobject.SaleItemDTO;

import java.util.ArrayList;

public class SaleTransactionService {
    private SaleTransaction saleTransaction;



    public SaleTransactionService(ServiceCreator serviceCreator) {

    }

    /**
     * Start a new sale on request by the <code> SaleController </code>
     * A new instance of <code> SaleTransaction </code> is created by
     * calling a enum class providing implementations of the <code> Factory </code>
     * interface.
     */
    public void startSale(ArrayList<EventObserver> observers) {
        SaleSpecification saleSpecification = SaleSpecificationFactory.DEFAULT_SALE.create(observers);
        saleTransaction = new SaleTransaction(saleSpecification);
        saleSpecification.getSaleState().nextState();
    }

    public void registerProduct(SaleItemDTO saleItemDTO) {

    }
}

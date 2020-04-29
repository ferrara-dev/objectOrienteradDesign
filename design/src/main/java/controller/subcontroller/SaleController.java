package controller.subcontroller;

import observer.EventObserver;
import service.modelservice.saleservice.SaleService;

import java.util.ArrayList;

public class SaleController {
    SaleService saleService;
    private static ArrayList<EventObserver> observers;

    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    /**
     * Call to <code> SaleService </code> to initiate a new
     * Sale.
     *
     * First creates the <code> Sale </code> object,
     * adds <code> EventListener </code> and then initiates
     * default values to sale attributes.
     */
    public void startSale( ){
        saleService.startSale();
        saleService.distributeObservers(observers);
        saleService.initSaleDefault();
    }

    /**
     * Call saleService to register a new product to the sale.
     *
     * @param itemId id of the product that is to be registered.
     * @param quantity amount of product units that are to be registered
     *                 to the sale.
     */
    public void initiateProductRegistration(int itemId, int quantity){
        saleService.registerProduct(itemId, quantity);
    }
    /**
     * Add <code> EventObserver </code> implementation to <code> observers </code>,
     * that are distributed to model objects of the type <code> ObservableModel </code>.
     */
    public void addObservers(EventObserver eventObserver){
        if(observers == null)
            observers = new ArrayList<>();
        observers.add(eventObserver);
    }

    public void endSale(){
        saleService.endSale();
    }

    public SaleService getSaleService() {
        return saleService;
    }
}

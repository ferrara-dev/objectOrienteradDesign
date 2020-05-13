package service;

import observer.modelobserver.EventObserver;
import service.discountservice.DiscountService;
import exception.exceptionhandler.ExceptionHandler;
import exception.exceptionhandler.ExceptionHandlingChain;
import startup.layer.ServiceCreator;
import util.datatransferobject.PaymentDTO;
import util.datatransferobject.SaleItemDTO;
import java.util.ArrayList;

/**
 * Class working as a mediator between the controller- and service layer.
 * Holds references to all service classes in order to create abstraction
 * between control- and service layer.
 */
public class ServiceFacade {
    private DiscountService discountService;
    private SaleService saleService;
    private ProductService productService;
    private EconomyService economyService;


    public ServiceFacade(ServiceCreator creator) {
        saleService = creator.getSaleService();
        productService = creator.getProductService();
        discountService = creator.getDiscountService();
        economyService = creator.getEconomyService();
    }

    public void startSale() {
        try {
            saleService.startSale();
        } catch (Exception e){
            ExceptionHandler.handle(e);
        }
    }

    public void registerProduct(SaleItemDTO saleItemDTO) {
        try {
            productService.registerProduct(saleItemDTO, saleService.getSaleInformation());
        } catch (Exception e){
            ExceptionHandler.handle(e);
        }
    }

    public void endSale() {
        try {
            saleService.endSale();
        } catch (Exception e){
            ExceptionHandler.handle(e);
        }
    }

    public void initPayment(PaymentDTO paymentDTO) {
        try {
            economyService.paySaleTransaction(paymentDTO, saleService.getSaleInformation());
        } catch (Exception e){
            ExceptionHandler.handle(e);
        }
    }

    public void requestDiscount(String customerId) {
        try {
            discountService.processDiscountRequest(customerId, saleService.getSaleInformation());
        } catch (Exception e){
            ExceptionHandler.handle(e);
        }
    }

    public void addObservers(ArrayList<EventObserver> eventObservers) {
        saleService.setEventObservers(eventObservers);
    }

    /**** Getters only used for testing ****/
    public EconomyService getEconomyService() {
        return economyService;
    }

    public SaleService getSaleService() {
        return saleService;
    }


}

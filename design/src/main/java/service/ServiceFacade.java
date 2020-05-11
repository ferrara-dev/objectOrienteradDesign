package service;

import observer.EventObserver;
import service.discountservice.DiscountService;
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
        discountService = new DiscountService(creator);
        economyService = new EconomyService();
    }

    public void startSale() {
        saleService.startSale();
    }

    public void registerProduct(SaleItemDTO saleItemDTO) {
        productService.registerProduct(saleItemDTO, saleService.getSaleInformation());
    }

    public void endSale() {
        saleService.endSale();
    }

    public void initPayment(PaymentDTO paymentDTO) {
        economyService.paySaleTransaction(paymentDTO, saleService.getSaleInformation());
    }

    public void requestDiscount(String customerId) {
        discountService.processDiscountRequest(customerId, saleService.getSaleInformation());
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

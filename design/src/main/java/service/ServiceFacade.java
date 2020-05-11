package service;

import observer.EventObserver;
import service.facadepattern.EconomyService;
import service.modelservice.discountservice.DiscountService;
import service.modelservice.productservice.ProductService;
import service.modelservice.saleservice.SaleService;
import startup.layer.ServiceCreator;
import util.datatransferobject.PaymentDTO;
import util.datatransferobject.SaleItemDTO;
import util.exception.notfoundexception.NotFoundException;
import util.exception.notfoundexception.ProductNotFoundException;

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
    private ExceptionService exceptionService;

    public ServiceFacade(ServiceCreator creator) {
        saleService = creator.getSaleService();
        productService = creator.getProductService();
        discountService = new DiscountService(creator);
        economyService = new EconomyService();
        exceptionService = new ExceptionService();
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
        try {
            discountService.processDiscountRequest(customerId, saleService.getSaleInformation());
        } catch (RuntimeException e) {
            exceptionService.handleException(e);
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

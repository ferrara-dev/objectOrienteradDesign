package startup.layer;


import factory.HandlerFactory;
import integration.PhysicalObjectsRepository;
import observer.modelobserver.EventObserver;
import service.*;
import service.discountservice.DiscountService;
import service.discountservice.discountidentifier.DiscountRequestHandler;


import java.util.ArrayList;


public class ServiceCreator {
    private ServiceFacade serviceFacade;
    private DiscountService discountService;
    private SaleService saleService;
    private CustomerService customerService;
    private ProductService productService;
    private EconomyService economyService;
    private PhysicalObjectsRepository physicalObjectsRepository;

    public ServiceCreator(){
        productService = new ProductService();
        saleService = new SaleService();
        customerService = new CustomerService();
        discountService = new DiscountService((DiscountRequestHandler) HandlerFactory.DISCOUNT_REQUEST_HANDLER.create());
        economyService = new EconomyService();
        serviceFacade = new ServiceFacade(this);
    }

    public void setUpObservers(ArrayList<EventObserver> eventObservers) {
        serviceFacade.addObservers(eventObservers);
    }

    public EconomyService getEconomyService() {
        return economyService;
    }

    public ServiceFacade getServiceFacade() {
        return serviceFacade;
    }

    public ProductService getProductService() {
        return productService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public SaleService getSaleService() {
        return saleService;
    }

}

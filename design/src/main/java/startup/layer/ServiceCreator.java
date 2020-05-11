package startup.layer;


import integration.PhysicalObjectsRepository;
import observer.EventObserver;
import service.ServiceFacade;
import service.ProductService;
import service.CustomerService;
import service.discountservice.DiscountService;
import service.SaleService;


import java.util.ArrayList;


public class ServiceCreator {
    private ServiceFacade serviceFacade;
    private DiscountService discountService;
    private SaleService saleService;
    private CustomerService customerService;
    private ProductService productService;
    private PhysicalObjectsRepository physicalObjectsRepository;

    public ServiceCreator(){
        productService = new ProductService();
        saleService = new SaleService(this);
        customerService = new CustomerService(this);
        discountService = new DiscountService(this);
        serviceFacade = new ServiceFacade(this);
    }

    public void setUpObservers(ArrayList<EventObserver> eventObservers) {
        serviceFacade.addObservers(eventObservers);
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

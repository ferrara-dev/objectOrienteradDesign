package startup.layer;


import integration.customerdb.CustomerRepository;
import integration.discountdb.DiscountRepository;
import integration.productdb.ProductRepository;
import integration.saledb.SaleLogHandler;
import service.modelservice.customerservice.CustomerDBService;
import service.modelservice.discountservice.DiscountDBService;
import service.modelservice.productservice.ProductDBService;
import service.modelservice.saleservice.SaleDBService;

/**
 * Creator used to initiate all the integrations services and provide references to them
 */
public class IntegrationServiceCreator {
    private SaleDBService saleDBService;
    private DiscountDBService discountDBService;
    private CustomerDBService customerDBService;
    private ProductDBService productDBService;

    public IntegrationServiceCreator() {
        saleDBService = new SaleDBService(new SaleLogHandler());
        discountDBService = new DiscountDBService(new DiscountRepository());
        customerDBService = new CustomerDBService(new CustomerRepository());
        productDBService = new ProductDBService(new ProductRepository());
    }

    public CustomerDBService getCustomerDBService() {
        return customerDBService;
    }

    public ProductDBService getProductDBService() {
        return productDBService;
    }

    public DiscountDBService getDiscountDBService() {
        return discountDBService;
    }

    public SaleDBService getSaleDBService() {
        return saleDBService;
    }
}

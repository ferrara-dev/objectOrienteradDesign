package startup.layer;

import service.modelservice.paymentservice.PaymentService;
import service.modelservice.productservice.ProductService;
import service.modelservice.customerservice.CustomerService;
import service.modelservice.discountservice.DiscountService;
import service.modelservice.saleservice.SaleService;
import service.PhysicalObjectsRepository;

public class ServiceCreator {
    private PhysicalObjectsRepository physicalObjectsRepository;
    private IntegrationServiceCreator integrationServiceCreator;
    private DiscountService discountService;
    private SaleService saleService;
    private CustomerService customerService;
    private ProductService productService;
    private PaymentService paymentService;

    public ServiceCreator(PhysicalObjectsRepository physicalObjectsRepository){
        this.physicalObjectsRepository = physicalObjectsRepository;
        integrationServiceCreator = new IntegrationServiceCreator();
        productService = new ProductService(this);
        saleService = new SaleService(this);
        customerService = new CustomerService(this);
        discountService = new DiscountService(this);
        paymentService = new PaymentService(this);
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

    public IntegrationServiceCreator getIntegrationServiceCreator() {
        return integrationServiceCreator;
    }

    public SaleService getSaleService() {
        return saleService;
    }

    public PhysicalObjectsRepository getPhysicalObjectsRepository() {
        return physicalObjectsRepository;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }
}

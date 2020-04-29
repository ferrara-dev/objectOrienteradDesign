package service.modelservice.saleservice;

import model.amount.Amount;
import service.modelservice.Service;
import service.modelservice.customerservice.CustomerService;
import service.PhysicalObjectsRepository;
import startup.layercreator.ServiceCreator;
import model.sale.Sale;
import observer.EventObserver;
import service.IntegrationService;
import service.modelservice.productservice.ProductService;

import java.util.ArrayList;
import java.util.Objects;

public class SaleService implements Service {
    private IntegrationService<Sale> saleDataBaseService;
    private ProductService productService;
    private PhysicalObjectsRepository physicalObjectsRepository;
    private CustomerService customerService;
    private Sale sale;

    public SaleService(ServiceCreator serviceCreator) {
        saleDataBaseService = serviceCreator.getIntegrationServiceFactory().getSaleDBService();
        productService = serviceCreator.getProductService();
        physicalObjectsRepository = serviceCreator.getPhysicalObjectsRepository();
    }

    public Sale getSale() {
        return sale;
    }

    /**
     * Start a new sale on request by the <code> SaleController </code>
     * If the call origins from the view, observers need to be added to the models
     * before calling <code> initSaleDefault() </code>.
     */
    public void startSale() {
        sale = new Sale();
        sale.startSale();
    }

    public void distributeObservers(ArrayList<EventObserver> observers) {
        if (Objects.nonNull(observers))
            for (EventObserver observer : observers) {
                sale.getSaleDetail().addObserver(observer);
                sale.addObserver(observer);
                sale.getCart().addObserver(observer);
                sale.getCost().addObserver(observer);
            }
    }

    /**
     * Call to initiate default values to field in <code> sale </code>
     */
    public void initSaleDefault() {
        sale.createDefault();
    }

    /**
     * Register a new product to the sale
     *
     * if <code> sale </code> is not initiated
     * before the method is called <code> IllegalStateException </code> is thrown.
     *
     * if<code> sale.isCompleted() </code> and <code> Sale.isActive() </code> ,
     * a product will be collected by calling <code> productService </code> and
     * then added to the <code> cart </code>.
     * @param itemId
     * @param quantity
     */

    public void registerProduct(int itemId, int quantity) {
        if (Objects.isNull(sale)) {
            throw new IllegalStateException();

        }

        if (!sale.getSaleDetail().isCompleted())
            if (sale.getSaleDetail().isActive()) {
                sale.getCart().add(productService.getProduct(itemId), quantity);
                sale.updateCost();
                updateRunningTotal();
            }
    }

    public void updateRunningTotal() {
        double newTotal = sale.getCost().getTotalCost();
        sale.setRunningTotal(newTotal);
    }

    public void endSale() {
        sale.getSaleDetail().setCompleted(true);
    }

    public void finalizeSale(double cashBack){
        sale.setCashBack(cashBack);
        sale.finishSale();
        if(sale.isDiscounted()){
            customerService.registerSaleToCustomer(sale);
        }
        saleDataBaseService.updateDB(sale);
        physicalObjectsRepository.printReceipt(sale);
    }

    public Amount getSaleCost(){
        return sale.getCost();
    }

    @Override
    public Service getInstance() {
        return this;
    }


}

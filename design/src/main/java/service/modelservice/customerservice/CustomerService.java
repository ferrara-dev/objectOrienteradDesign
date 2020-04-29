package service.modelservice.customerservice;

import model.customer.Member;
import service.modelservice.Service;
import startup.layercreator.ServiceCreator;
import model.sale.Sale;
import service.IntegrationService;
import service.modelservice.saleservice.SaleService;

public class CustomerService implements Service {
    public static final Long HASH_KEY_ID = 13L;
    private SaleService saleService;
    private IntegrationService <Member> dataBaseService;
    private Member member;
    public CustomerService(ServiceCreator serviceCreator){
        saleService = serviceCreator.getSaleService();
        dataBaseService = serviceCreator.getIntegrationServiceFactory().getCustomerDBService();
    }

    public void initDefault(){

    }

    public Member findCustomer(String customerId){
        member = dataBaseService.getFromDB(customerId);
        member.setSaleInProgress(saleService.getSale());
        return member;
    }

    public void handleCustomerRequest(Service service){

    }

    /**
     * Register a customer to a completed sale,
     * @param sale
     */
    public void registerSaleToCustomer(Sale sale){
        member.addSaleToRegisteredPurchases(sale);
    }

    @Override
    public Service getInstance() {
        return this;
    }
}

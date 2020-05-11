package service;

import model.customer.Member;
import startup.layer.ServiceCreator;

/**
 * Customer service class that used to perform operation on <code> Member </code>objects
 */
public class CustomerService implements Service {
    public static final Long HASH_KEY_ID = 13L;
    private SaleService saleService;
    private Member member;
    public CustomerService(ServiceCreator serviceCreator){
        saleService = serviceCreator.getSaleService();
    }

    public void initDefault(){

    }

    /**
     * Find a customer and set the sale in progress
     * @param customerId
     * @return
     */
    public Member findCustomer(String customerId){
        //member.setSaleInProgress(saleService.getSale());
        return member;
    }

    public void handleCustomerRequest(Service service){

    }



}

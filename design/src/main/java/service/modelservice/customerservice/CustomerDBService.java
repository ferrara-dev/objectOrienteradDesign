package service.modelservice.customerservice;

import integration.DataBaseHandler;
import integration.customerdb.CustomerRepository;
import model.customer.Member;
import model.customer.CustomerId;
import service.IntegrationService;
import util.exception.NotFoundException;

public class CustomerDBService implements IntegrationService<Member> {
    public static final Long HASH_KEY_ID = 124L;
    private final DataBaseHandler<Member, Member> customerDataBaseHandler;

    public CustomerDBService() {
        this.customerDataBaseHandler = CustomerRepository.getInstance();
    }

    @Override
    public void updateDB(Object object) {

    }

    @Override
    public Member getFromDB(Object customerId) {
        try {
            Member member = customerDataBaseHandler.collect((String) customerId);
            CustomerId customerIdentification = new CustomerId();
            customerIdentification.setPersonalNumber((String) customerId);
            member.setCustomerId(customerIdentification);
            return member;
        }

        catch (NotFoundException ex){
            throw ex;
        }
    }

    @Override
    public boolean find(Object object) {
        return customerDataBaseHandler.find((String) object);
    }
}

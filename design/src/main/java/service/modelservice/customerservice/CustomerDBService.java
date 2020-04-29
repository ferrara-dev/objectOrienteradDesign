package service.modelservice.customerservice;

import integration.DataBaseHandler;
import model.customer.Member;
import model.customer.CustomerId;
import service.IntegrationService;
import util.exception.NotFoundException;

public class CustomerDBService implements IntegrationService<Member> {
    public static final Long HASH_KEY_ID = 124L;
    private final DataBaseHandler<Member, Member> customerDataBaseHandler;

    public CustomerDBService(DataBaseHandler<Member, Member> customerDBHandler) {
        this.customerDataBaseHandler = customerDBHandler;
    }

    @Override
    public void updateDB(Object object) {

    }

    @Override
    public Member getFromDB(Object customerId) {
        try {
            Member member = customerDataBaseHandler.collect((String) customerId);
            member.setCustomerId(new CustomerId((String) customerId));
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

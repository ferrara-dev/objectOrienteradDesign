package factory;

import util.datatransferobject.CustomerDTO;
import model.customer.Member;

/**
 * CustomerFactory used to create a new <code> Member </code> and store it to the
 * customer database.
 */
public class CustomerFactory implements Factory<Member, CustomerDTO>{

    @Override
    public Member create(CustomerDTO customerDTO) {
        Member member = new Member();
        member.setAttributes(customerDTO);
        return member;
    }
}

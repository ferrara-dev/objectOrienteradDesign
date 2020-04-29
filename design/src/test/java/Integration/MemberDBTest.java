package Integration;

import util.datatransferobject.CustomerDTO;
import integration.DataBaseHandler;
import integration.customerdb.CustomerDBHandler;
import model.customer.Member;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MemberDBTest {
    /**
     * 1. Test collecting a registered <code> Customer </code> from the jsonCustomerTable
     * 2. Test registering a new <code> Customer </code> to the jsonCustomerTable
     */
    @Test
    public void testCustomerDBHandler(){
        /* 1 */
        DataBaseHandler<Member, Member> dataBaseHandler = new CustomerDBHandler();
        Member member = dataBaseHandler.collect("940412-1395");
        assertNotNull(member);
        assertEquals(member.getName(),"Samuel");
        assertEquals(member.getClass(), Member.class);

        /* 2 */
        Member newMember = new Member();
        newMember.setAttributes(new CustomerDTO("Adam", "112233-4455","adam@kth.se"));
        dataBaseHandler.register(newMember.getCustomerId().getPersonalNumber(), newMember);
        Member retrievedMember = dataBaseHandler.collect("112233-4455");
        assertEquals(retrievedMember.getName(), "Adam");
        assertEquals(retrievedMember.getCustomerId(),"112233-4455");
        assertEquals(retrievedMember.getClass(), Member.class);
    }
}

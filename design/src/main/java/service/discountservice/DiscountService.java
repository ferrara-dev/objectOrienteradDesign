package service.discountservice;

import factory.discountstrategyFactory.DiscountRuleFactory;
import integration.customerdb.CustomerRepository;
import integration.discountdb.DiscountRepository;
import model.customer.Member;
import model.customer.customerrequest.MemberDiscountRequest;
import model.discount.discountrule.DiscountRule;
import model.sale.saleinformation.SaleTransaction;
import service.discountservice.discountidentifier.*;
import service.visitor.Visitor;
import factory.VisitorFactory;
import util.Calendar;
import util.datatransferobject.DiscountDTO;

import java.util.List;

public class DiscountService{
    public static final Long HASH_KEY_ID = 12L;
    private DiscountRequestHandler requestHandler;
    private Visitor visitor;

    public DiscountService(DiscountRequestHandler discountRequestHandler) {
        requestHandler = discountRequestHandler;
    }

    /**
     * Initiates a new discountRequest, calls customerService
     * to find out if the requesting customer is a registered
     * member.
     * If not, <code> NotFoundException </code>  is
     * throw from <code> CustomerDBHandler </code>.
     * <p>
     * If the customer is found in the customer database,
     * a new <code> MemberDiscountRequest </code> is initiated.
     *
     * @param customerId
     */
    public void processDiscountRequest(String customerId, SaleTransaction saleTransaction) {
            MemberDiscountRequest memberDiscountRequest;
            memberDiscountRequest = createRequest(customerId);
            memberDiscountRequest.setSaleTransaction(saleTransaction);
            findAvailableDiscounts(memberDiscountRequest);
            handleRequest(memberDiscountRequest);
    }

    private MemberDiscountRequest createRequest(String customerId){
        Member member = CustomerRepository.getInstance().collect(customerId);
        MemberDiscountRequest memberDiscountRequest = new MemberDiscountRequest(member);
        return memberDiscountRequest;
    }

    private void findAvailableDiscounts(MemberDiscountRequest memberDiscountRequest){
            List<DiscountDTO> discountDTOs = DiscountRepository.getInstance().collect(Calendar.getDayOfTheWeek());
            for (DiscountDTO discountDTO : discountDTOs)
                for (DiscountRule discountRule : DiscountRuleFactory.getInstance().create(discountDTO))
                    memberDiscountRequest.addDiscountRule(discountRule);
    }

    private void handleRequest(MemberDiscountRequest memberDiscountRequest){
        requestHandler.handle(memberDiscountRequest);
        visitor = VisitorFactory.COST_VISITOR.getVisitor();
        visitor.setData(memberDiscountRequest.getSaleTransaction().getCart().sequenceIterator());
        memberDiscountRequest.getSaleTransaction().getCost().acceptVisitor(visitor);
    }
}

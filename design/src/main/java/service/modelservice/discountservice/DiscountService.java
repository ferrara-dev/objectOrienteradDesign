package service.modelservice.discountservice;

import factory.discountstrategyFactory.DiscountRuleFactory;
import integration.customerdb.CustomerRepository;
import integration.discountdb.DiscountRepository;
import model.customer.Member;
import model.customer.customerrequest.MemberDiscountRequest;
import model.discount.discountrule.DiscountRule;
import model.transaction.saleTransaction.SaleTransaction;
import service.modelservice.discountservice.discountRequestHandler.BulkDiscountRequestHandler;
import service.modelservice.discountservice.discountRequestHandler.TotalCostDiscountRequestHandler;
import service.modelservice.discountservice.discountidentifier.*;
import service.modelservice.Service;
import service.visitor.Visitor;
import factory.VisitorFactory;
import startup.layer.ServiceCreator;
import util.Calendar;
import util.datatransferobject.DiscountDTO;
import util.exception.notfoundexception.NotFoundException;

import java.util.List;

public class DiscountService implements Service {
    public static final Long HASH_KEY_ID = 12L;
    private DiscountRequestHandler requestHandler;
    private Visitor visitor;

    public DiscountService(ServiceCreator serviceCreator) {
        requestHandler = new DiscountRuleIdentifier(
                new TotalCostDiscountRequestHandler(null),
                new BulkDiscountRequestHandler(null));
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
    public void processDiscountRequest(String customerId, SaleTransaction saleTransaction) throws NotFoundException {
        try {
            MemberDiscountRequest memberDiscountRequest = createRequest(customerId);

            memberDiscountRequest.setSaleTransaction(saleTransaction);

            findAvailableDiscounts(memberDiscountRequest);

            handleRequest(memberDiscountRequest);
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MemberDiscountRequest createRequest(String customerId) throws NotFoundException{
        try {
        Member member = CustomerRepository.getInstance().collect(customerId);
            MemberDiscountRequest memberDiscountRequest = new MemberDiscountRequest(member);
            return memberDiscountRequest;
        } catch (NotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void findAvailableDiscounts(MemberDiscountRequest memberDiscountRequest) throws NotFoundException{
        try {
            List<DiscountDTO> discountDTOs = DiscountRepository.getInstance().collect(Calendar.getDayOfTheWeek());
            for (DiscountDTO discountDTO : discountDTOs)
                for (DiscountRule discountRule : DiscountRuleFactory.getInstance().create(discountDTO))
                    memberDiscountRequest.addDiscountRule(discountRule);
        } catch (NotFoundException exception){
            exception.printStackTrace();
            throw exception;
        }
    }

    private void handleRequest(MemberDiscountRequest memberDiscountRequest) throws Exception {
        requestHandler.handle(memberDiscountRequest);
        visitor = VisitorFactory.COST_VISITOR.getVisitor();
        visitor.setData(memberDiscountRequest.getSaleTransaction().getCart().getSequenceIterator());
        memberDiscountRequest.getSaleTransaction().getCost().acceptVisitor(visitor);
    }
}

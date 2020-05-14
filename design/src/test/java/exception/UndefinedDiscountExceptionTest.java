package exception;

import exception.businessruleexception.BusinessLogicException;
import exception.businessruleexception.UndefinedDiscountException;
import exception.exceptionhandler.ExceptionHandler;
import exception.notfoundexception.NotFoundException;
import factory.CustomerFactory;
import factory.HandlerFactory;
import factory.IntegrationFactory;
import model.customer.Member;
import model.customer.customerrequest.MemberDiscountRequest;
import model.discount.discountrule.DiscountRule;
import model.discount.discountrule.pricediscountrule.PriceDiscountRule;
import model.discount.discounttypes.defaultdiscount.NoItemDiscount;
import model.discount.discounttypes.defaultdiscount.NoPriceDiscount;
import org.junit.Before;
import org.junit.Test;
import service.discountservice.discountRequestHandler.BulkDiscountRequestHandler;
import service.discountservice.discountRequestHandler.ItemDiscountRequestHandler;
import service.discountservice.discountRequestHandler.PriceDiscountHandler;
import service.discountservice.discountRequestHandler.TotalCostDiscountRequestHandler;
import service.discountservice.discountidentifier.DiscountRuleIdentifier;
import util.datatransferobject.CustomerDTO;
import util.sequence.ListSequence;
import view.exceptionview.ExceptionView;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UndefinedDiscountExceptionTest {
    ExceptionView exceptionView;
    DiscountRuleIdentifier ruleIdentifier;
    @Before
    public void startUp() {
        exceptionView = new ExceptionView();
        ExceptionHandler.createExceptionHandlingChain(exceptionView);
        ruleIdentifier = (DiscountRuleIdentifier) HandlerFactory.DISCOUNT_REQUEST_HANDLER.create();
    }

    /**
     * Test invalid discount rule exception
     */
    @Test
    public void testInvalidRule1() {
      ruleIdentifier.handle(mockUpDiscountRequest());
    }

    /**
     * Test if UndefinedDiscountException is thrown
     */
    @Test(expected = UndefinedDiscountException.class)
    public void testInvalidRule2() {
        PriceDiscountHandler priceDiscountHandler = new TotalCostDiscountRequestHandler(null);
        priceDiscountHandler.handleRequest(mockUpDiscountRequest());
    }

    /**
     * Test if UndefinedDiscountException is thrown
     */
    @Test(expected = UndefinedDiscountException.class)
    public void testInvalidRule3() {
        ItemDiscountRequestHandler itemDiscountRequestHandler = new BulkDiscountRequestHandler(null);
        itemDiscountRequestHandler.handleRequest(mockUpDiscountRequest());
    }

    private MemberDiscountRequest mockUpDiscountRequest(){
        Member member = new Member();
        member.setAttributes(new CustomerDTO("A","1","abc"));
        MemberDiscountRequest memberDiscountRequest = new MemberDiscountRequest(member);
        FakePriceDiscount fakePriceDiscount  = new FakeDiscountRule();
        memberDiscountRequest.addDiscountRule(fakePriceDiscount);
        return memberDiscountRequest;
    }
    private interface FakePriceDiscount extends PriceDiscountRule {

    }

    private class FakeDiscountRule implements FakePriceDiscount{

        @Override
        public String getDiscountClass() {
            return getClass().getSimpleName();
        }

        @Override
        public List<String> getAvailable() {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public double getMinimumSpend() {
            return 0;
        }

        @Override
        public boolean checkRequierments(double obj) {
            return false;
        }

        @Override
        public double getRequierment() {
            return 0;
        }

        @Override
        public double getDiscountRate() {
            return 0;
        }
    }
}

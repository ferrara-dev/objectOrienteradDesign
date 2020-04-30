package service.modelservice.discountservice;

import model.customer.Member;
import model.customer.MemberDiscountRequest;
import model.discount.Discount;
import model.discount.ValidMemberDiscount;
import model.discount.discountrule.DiscountRule;
import service.handlerpattern.requesthandler.BulkDiscountRequestHandler;
import service.handlerpattern.requesthandler.TotalCostDiscountRequestHandler;
import service.handlerpattern.discountidentifier.DiscountRequestHandler;
import service.handlerpattern.discountidentifier.DiscountRuleIdentifier;
import service.modelservice.Service;
import startup.layer.ServiceCreator;
import service.IntegrationService;
import service.modelservice.customerservice.CustomerService;
import service.modelservice.saleservice.SaleService;
import util.exception.NotFoundException;
import util.exception.UndefinedDiscountException;

import java.util.ArrayList;
import java.util.Objects;

public class DiscountService implements Service {
    public static final Long HASH_KEY_ID = 12L;
    private IntegrationService<ArrayList<DiscountRule>> discountDBService;
    private SaleService saleService;
    private CustomerService customerService;
    private DiscountCalculator discountCalculator;
    private MemberDiscountRequest memberDiscountRequest;

    private DiscountRequestHandler requestHandler;

    public DiscountService(ServiceCreator serviceCreator) {
        saleService = serviceCreator.getSaleService();
        customerService = serviceCreator.getCustomerService();
        discountDBService = serviceCreator.getIntegrationServiceCreator().getDiscountDBService();

        requestHandler = new DiscountRuleIdentifier(
                new TotalCostDiscountRequestHandler(null),
                new BulkDiscountRequestHandler(null));

        discountCalculator = new DiscountCalculator();
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
    public void processDiscountRequest(String customerId) {
        try {
            Member member = customerService.findCustomer(customerId);
            ArrayList<DiscountRule> discountRules = discountDBService.getFromDB(util.Calendar.getDayOfTheWeek());
            MemberDiscountRequest memberDiscountRequest = new MemberDiscountRequest(member);
            memberDiscountRequest.setDiscountRuleListSequence(discountRules);
            requestHandler.handleRequest(memberDiscountRequest);

                /*
            for (DiscountRule discountRule : discountRules) {
                memberDiscountRequest.setRequestedDiscount(discountRule);
                try {
                    requestHandler.handleRequest(memberDiscountRequest);
                }catch (UndefinedDiscountException ex){
                    ex.getMessage();
                    continue;
                }
                */
                saleService.getSale().setCart(memberDiscountRequest.getSaleCart());
                saleService.getSale().setCost(memberDiscountRequest.getSaleCost());
            } catch (NotFoundException ex) {
                throw ex;
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
        /**
         * Calls <code> DiscountCalculator </code> to apply all validated
         * discounts to the current sale.
         */
        public void applyValidDiscounts () {
        /*
        ArrayList<Discount> discounts = memberDiscountRequest.getValidatedDiscounts();
        if (Objects.nonNull(discounts)) {
            for (Discount discount : discounts) {
                discountCalculator.applyDiscount(saleService.getSale(), discount);
            }
        }
        saleService.updateRunningTotal();
        saleService.getSale().updateCost();
        */

        }

        /**
         * Processes the collected discounts.
         * <p>
         * calls <code> discountCalculator </code>
         * to validate and generate discounts from collected discount rules.
         */
        public void processRequest () {
            ArrayList<DiscountRule> discountRules = discountDBService.getFromDB(util.Calendar.getDayOfTheWeek());
            discountCalculator.generateDiscounts(memberDiscountRequest, discountRules);
        }

        @Override
        public Service getInstance () {
            return this;
        }

        public MemberDiscountRequest getMemberDiscountRequest () {
            return memberDiscountRequest;
        }
    }

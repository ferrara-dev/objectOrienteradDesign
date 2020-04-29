package service.modelservice.discountservice;

import model.customer.Member;
import model.customer.MemberDiscountRequest;
import model.discount.Discount;
import model.discount.discountrule.DiscountRule;
import service.modelservice.Service;
import startup.layercreator.ServiceCreator;
import service.IntegrationService;
import service.modelservice.customerservice.CustomerService;
import service.modelservice.saleservice.SaleService;
import util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Objects;

public class DiscountService implements Service {
    public static final Long HASH_KEY_ID = 12L;
    private IntegrationService<ArrayList<DiscountRule>> discountDBService;

    private SaleService saleService;
    private CustomerService customerService;
    private final DiscountCalculator discountCalculator;
    private MemberDiscountRequest memberDiscountRequest;

    public DiscountService(ServiceCreator serviceCreator) {
        saleService = serviceCreator.getSaleService();
        customerService = serviceCreator.getCustomerService();
        discountDBService = serviceCreator.getIntegrationServiceFactory().getDiscountDBService();
        discountCalculator = new DiscountCalculator();
    }

    /**
     * Initiates a new discountRequest, calls customerService
     * to find out if the requesting customer is a registered
     * member.
     * If not, <code> NotFoundException </code>  is
     * throw from <code> CustomerDBHandler </code>.
     *
     * If the customer is found in the customer database,
     * a new <code> MemberDiscountRequest </code> is initiated.
     * @param customerId
     */
    public void initiateDiscountRequest(String customerId) {
        try{
            Member member = customerService.findCustomer(customerId);
            memberDiscountRequest = new MemberDiscountRequest(member);
        }
        catch (NotFoundException ex){
            throw ex;
        }
    }

    /**
     * Calls <code> DiscountCalculator </code> to apply all validated
     * discounts to the current sale.
     */
    public void applyValidDiscounts() {
        ArrayList<Discount> discounts = memberDiscountRequest.getValidatedDiscounts();
        if(Objects.nonNull(discounts)){
            for(Discount discount: discounts){
                discountCalculator.applyDiscount(saleService.getSale(),discount);
            }
        }
        saleService.updateRunningTotal();
        saleService.getSale().updateCost();
    }

    /**
     * Processes the collected discounts.
     *
     * calls <code> discountCalculator </code>
     * to validate and generate discounts from collected discount rules.
     */
    public void processRequest() {
        ArrayList<DiscountRule> discountRules = discountDBService.getFromDB(util.Calendar.getDayOfTheWeek());
        discountCalculator.generateDiscounts(memberDiscountRequest,discountRules);
    }

    @Override
    public Service getInstance() {
        return this;
    }

}

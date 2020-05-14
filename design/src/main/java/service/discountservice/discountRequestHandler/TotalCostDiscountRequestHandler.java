package service.discountservice.discountRequestHandler;
import model.amount.RunningTotal;
import model.customer.customerrequest.MemberDiscountRequest;
import model.discount.discountrule.DiscountRule;
import model.discount.discountrule.pricediscountrule.PriceDiscountRule;
import model.discount.discountrule.pricediscountrule.TotalCostDiscountRule;
import model.discount.discounttypes.pricediscount.TotalCostDiscount;
import exception.businessruleexception.UndefinedDiscountException;
import java.util.Objects;

/**
 * Implementation of the <code> PriceDiscountHandler </code>.
 * This class is part of a chain of responsibility and is responsible
 * for processing <code> TotalCostDiscountRule </code> objects.
 */
public class TotalCostDiscountRequestHandler extends PriceDiscountHandler {

    public TotalCostDiscountRequestHandler(PriceDiscountHandler successor) {
        super(successor);
    }

    @Override
    public void handleRequest(MemberDiscountRequest memberDiscountRequest) {
        DiscountRule currentRule = memberDiscountRequest.getDiscountRuleListSequence().sequenceIterator().getCurrentItem();
        if(currentRule instanceof TotalCostDiscountRule){
            RunningTotal finalCost = memberDiscountRequest.getSaleTransaction().getCost().getRunningTotal();
            if(currentRule.checkRequierments(finalCost.getNumber().doubleValue())){
                double discountRate = currentRule.getDiscountRate();
                TotalCostDiscount totalCostDiscount = new TotalCostDiscount((PriceDiscountRule) currentRule);
                totalCostDiscount.setTotalPriceReduction((discountRate)*finalCost.getNumber().doubleValue());
                memberDiscountRequest.getSaleTransaction().getCost().setPriceDiscount(totalCostDiscount);
            }
        }
        else{
            if(Objects.nonNull(successor))
                successor.handleRequest(memberDiscountRequest);
            else throw new UndefinedDiscountException("Discount rule is undefined : " + currentRule.getDiscountClass());
        }
    }
}

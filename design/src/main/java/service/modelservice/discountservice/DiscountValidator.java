package service.modelservice.discountservice;

import model.customer.MemberDiscountRequest;
import model.discount.Discount;
import model.discount.discountrule.DiscountRule;
import model.discount.discountrule.itemdiscountrule.BulkDiscountRule;
import model.discount.discountrule.itemdiscountrule.ItemDiscountRule;
import model.discount.discountrule.pricediscountrule.PriceDiscountRule;
import model.discount.discountrule.pricediscountrule.TotalCostDiscountRule;
import model.discount.discounttypes.itemdiscount.BulkDiscount;
import model.discount.discounttypes.pricediscount.TotalCostDiscount;
import util.exception.InvalidRequestException;


/**
 * Performs operations to validate a requested discount by
 * comparing information from the sale that the customer
 * requests a discount on and the requirements specified
 * in the <code> DiscountRule </code>.
 * <p>
 * Checks for type of <code> DiscountRule </code>
 * If the request meets the given requirement, an implementation of
 * <code> Discount </code> interface corresponding to the rule type
 * is returned.
 */
public class DiscountValidator {

    public Discount validateRequest(MemberDiscountRequest discountRequest, DiscountRule discountRule) {
        /*
        if (discountRule instanceof BulkDiscountRule) {
            int id = ((ItemDiscountRule) discountRule).getItemId();
            boolean containsProduct = discountRequest.getCurrentSale().getCart().contains(id);
            if (containsProduct) {
                if (discountRule.checkRequierments(discountRequest.getCurrentSale().getCart().getItem(id).getQuantity()))
                    return new BulkDiscount((ItemDiscountRule) discountRule);
            }
        } else if (discountRule instanceof TotalCostDiscountRule) {
            double spent = discountRequest.getCurrentSale().getCost().getTotalCost();
            if (discountRule.checkRequierments(spent))
                return new TotalCostDiscount((PriceDiscountRule) discountRule);
        }

        throw new InvalidRequestException();
    }
    */
        return null;
    }
}

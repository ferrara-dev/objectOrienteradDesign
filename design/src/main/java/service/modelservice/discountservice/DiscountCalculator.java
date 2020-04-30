package service.modelservice.discountservice;

import model.customer.MemberDiscountRequest;
import model.discount.Discount;
import model.discount.discountrule.DiscountRule;
import model.discount.discounttypes.itemdiscount.ItemDiscount;
import model.discount.discounttypes.pricediscount.PriceDiscount;
import model.sale.Sale;
import service.handlerpattern.discountidentifier.ItemReqHandler;
import util.exception.NotFoundException;


import java.util.ArrayList;

/**
 * Service class that computes and sets discounts in a custom discount request
 */
public class DiscountCalculator {
    private DiscountValidator discountValidator;
    private ItemReqHandler itemReqHandler;

    public DiscountCalculator() {
        discountValidator = new DiscountValidator();

    }

    /**
     * Genereates discounts from a discount request and a list of discount rules
     * Calls <code> discountValidator </code> to validate that the customer is
     * valid for the discount.
     *
     * @param discountRequest
     * @param discountRules
     */
    public void generateDiscounts(MemberDiscountRequest discountRequest, ArrayList<DiscountRule> discountRules) {
        for (DiscountRule discountRule : discountRules) {
            try {
                Discount discount = discountValidator.validateRequest(discountRequest, discountRule);
                /**
                 if (discount instanceof ItemDiscount) {
                 ItemDiscount itemDiscount = (ItemDiscount) discount;
                 double productPrice = discountRequest.getCurrentSale().getCart().getItem(itemDiscount.getDiscountedItem()).getProduct().getTotalPrice();
                 double totalPriceReduction =  itemDiscount.getRequirement()*productPrice*itemDiscount.getDiscountRule().getDiscountRate();
                 itemDiscount.setTotalPriceReduction(totalPriceReduction);
                 discountRequest.addValidatedDiscounts(discount);
                 } else if (discount instanceof PriceDiscount) {
                 PriceDiscount priceDiscount = (PriceDiscount) discount;
                 double totalPriceReduction = priceDiscount.getDiscountRule().getDiscountRate() * discountRequest.getCurrentSale().getCost().getTotalCost();
                 priceDiscount.setTotalPriceReduction(totalPriceReduction);
                 discountRequest.addValidatedDiscounts(priceDiscount);
                 }
                 } catch (InvalidRequestException invalidRequestException) {
                 continue;
                 }

                 }
                 */
            } catch (NotFoundException ex) {

            }
        }
    }

    public void applyDiscount(Sale sale, Discount discount) {
        if (discount instanceof ItemDiscount) {
            int itemId = ((ItemDiscount) discount).getDiscountedItem();
            sale.getCart().addItemDiscount(itemId, (ItemDiscount) discount);

        } else if (discount instanceof PriceDiscount) {
            sale.getCost().setPriceDiscount((PriceDiscount) discount);
        }
    }

}

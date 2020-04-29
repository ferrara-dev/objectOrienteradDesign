package service.modelservice.discountservice;
import model.customer.MemberDiscountRequest;
import model.discount.Discount;
import model.discount.discountrule.DiscountRule;
import model.discount.discounttypes.itemdiscount.ItemDiscount;
import model.discount.discounttypes.pricediscount.PriceDiscount;
import model.sale.Sale;
import util.exception.InvalidRequestException;

import java.util.ArrayList;

public class DiscountCalculator {
    private DiscountValidator discountValidator;

    public DiscountCalculator() {
        discountValidator = new DiscountValidator();
    }

    public void generateDiscounts(MemberDiscountRequest discountRequest, ArrayList<DiscountRule> discountRules) {
        for (DiscountRule discountRule : discountRules) {
            try {
                    Discount discount = discountValidator.validateRequest(discountRequest, discountRule);
                if (discount instanceof ItemDiscount) {
                    ItemDiscount itemDiscount = (ItemDiscount) discount;
                    double productPrice = discountRequest.getCurrentSale().getCart().getItem(itemDiscount.getDiscountedItem()).getProduct().getTotalPrice();
                    double totalPriceReduction = itemDiscount.getDiscountPolicy().getMinimumAmountOfItems()*productPrice*itemDiscount.getDiscountPolicy().getDiscountRate();
                    itemDiscount.setTotalPriceReduction(totalPriceReduction);
                    discountRequest.addRequestedDiscounts(discount);
                } else if (discount instanceof PriceDiscount) {
                    PriceDiscount priceDiscount = (PriceDiscount) discount;
                    double totalPriceReduction = priceDiscount.getDiscountPolicy().getDiscountRate() * discountRequest.getCurrentSale().getCost().getTotalCost();
                    priceDiscount.setTotalPriceReduction(totalPriceReduction);
                    discountRequest.addRequestedDiscounts(priceDiscount);
                }
            } catch (InvalidRequestException invalidRequestException) {
                continue;
            }
        }


    }

    public void applyDiscount(Sale sale, Discount discount) {
        if(discount instanceof ItemDiscount){
            int itemId = ((ItemDiscount) discount).getDiscountedItem();
            sale.getCart().addItemDiscount(itemId,(ItemDiscount) discount);

        }

        else if(discount instanceof PriceDiscount){
            sale.getCost().setPriceDiscount((PriceDiscount) discount);
        }
    }

}

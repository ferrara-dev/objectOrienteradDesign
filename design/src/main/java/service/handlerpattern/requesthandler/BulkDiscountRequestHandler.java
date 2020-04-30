package service.handlerpattern.requesthandler;

import model.amount.Cost;
import model.customer.MemberDiscountRequest;
import model.discount.ValidMemberDiscount;
import model.discount.discountrule.itemdiscountrule.BulkDiscountRule;
import model.discount.discounttypes.itemdiscount.BulkDiscount;
import model.discount.discounttypes.itemdiscount.ItemDiscount;
import model.sale.Cart;
import service.handlerpattern.discountidentifier.ItemReqHandler;
import util.exception.NotFoundException;
import util.exception.UndefinedDiscountException;

import java.util.Objects;


/**
 * Handles
 */
public class BulkDiscountRequestHandler extends ItemReqHandler {

    public BulkDiscountRequestHandler(ItemReqHandler successor) {
        super(successor);
    }

    @Override
    public void handleRequest(MemberDiscountRequest memberDiscountRequest) {
        if (memberDiscountRequest.getRequestedDiscount() instanceof BulkDiscountRule) {
            BulkDiscountRule bulkDiscountRule = (BulkDiscountRule) memberDiscountRequest.getRequestedDiscount();
            Cart cart = memberDiscountRequest.getSaleCart();
            int itemId = bulkDiscountRule.getItemId();

            try{
                int actualQuantity = cart.getItem(itemId).getQuantity();
                if(bulkDiscountRule.checkRequierments(actualQuantity));
                double price = cart.getItem(itemId).getTotalPrice();
                double discountRate = bulkDiscountRule.getDiscountRate();
                double totalPriceReduction = discountRate * price;
                BulkDiscount bulkDiscount = new BulkDiscount(bulkDiscountRule);
                bulkDiscount.setTotalPriceReduction(totalPriceReduction);
                cart.addItemDiscount(itemId,bulkDiscount);
            } catch (NotFoundException e){
                return;
            }

        } else {
            if (Objects.nonNull(successor))
                successor.handleRequest(memberDiscountRequest);
            else
                throw new UndefinedDiscountException();
        }
    }
}

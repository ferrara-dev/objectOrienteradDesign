package service.modelservice.discountservice.discountRequestHandler;

import model.customer.customerrequest.MemberDiscountRequest;
import model.discount.discountrule.DiscountRule;
import model.discount.discountrule.itemdiscountrule.BulkDiscountRule;
import model.discount.discounttypes.itemdiscount.BulkDiscount;
import model.discount.discounttypes.itemdiscount.ItemDiscount;
import model.sale.SaleItem;
import model.sale.saleinformation.ProductCart;
import util.exception.businessruleexception.UndefinedDiscountException;
import util.sequence.SequenceIterator;

import java.util.Objects;


/**
 * Handles a requested bulk discount
 */
public class BulkDiscountRequestHandler extends ItemDiscountRequestHandler {

    public BulkDiscountRequestHandler(ItemDiscountRequestHandler successor) {
        super(successor);

    }

    @Override
    public void handleRequest(MemberDiscountRequest memberDiscountRequest) {
        DiscountRule currentRule = memberDiscountRequest.getDiscountRuleListSequence().getSequenceIterator().getCurrentItem();
        if (currentRule instanceof BulkDiscountRule) {
            BulkDiscountRule bulkDiscountRule = (BulkDiscountRule) currentRule;
            ProductCart productCart = memberDiscountRequest.getSaleTransaction().getCart();
            int requiredItemId = bulkDiscountRule.getItemId();
            SequenceIterator<SaleItem> cartIterator = productCart.getSequenceIterator();
            cartIterator.firstItem();
            while (!cartIterator.isOver()) {
                if (cartIterator.getCurrentItem().getProduct().getItemId() == requiredItemId) {
                    if (bulkDiscountRule.checkRequierments(cartIterator.getCurrentItem().getQuantity())) {
                        ItemDiscount itemDiscount = new BulkDiscount(bulkDiscountRule);
                        itemDiscount.setTotalPriceReduction(cartIterator.getCurrentItem().getTotalPrice().doubleValue() * bulkDiscountRule.getDiscountRate());
                        cartIterator.getCurrentItem().setItemDiscount(itemDiscount);
                        break;
                    }
                }
                cartIterator.nextItem();
            }
                cartIterator.firstItem();

        } else {
            if (Objects.nonNull(successor))
                successor.handleRequest(memberDiscountRequest);
            else
                throw new UndefinedDiscountException();
        }
    }
}

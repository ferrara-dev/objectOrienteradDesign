package service.modelservice.discountservice.discountidentifier;

import model.customer.customerrequest.MemberDiscountRequest;
import model.discount.discountrule.DiscountRule;
import model.discount.discountrule.itemdiscountrule.ItemDiscountRule;
import model.discount.discountrule.pricediscountrule.PriceDiscountRule;
import service.modelservice.discountservice.discountRequestHandler.ItemDiscountRequestHandler;
import util.sequence.ListSequence;
import util.sequence.ListSequenceIterator;
import service.modelservice.discountservice.discountRequestHandler.TotalCostDiscountRequestHandler;
import util.exception.UndefinedDiscountException;

import java.util.Objects;

public class DiscountRuleIdentifier extends DiscountRequestHandler {

    public DiscountRuleIdentifier(TotalCostDiscountRequestHandler successor1, ItemDiscountRequestHandler successor2) {
        super(successor1, successor2);
    }

    @Override
    public void handle(MemberDiscountRequest memberDiscountRequest) {
        ListSequence<DiscountRule> ruleListSequence = memberDiscountRequest.getDiscountRuleListSequence();
        ListSequenceIterator<DiscountRule> iterator = (ListSequenceIterator<DiscountRule>) ruleListSequence.getSequenceIterator();

        iterator.firstItem();
        while (!iterator.isOver()) {
            DiscountRule discountRule = iterator.getCurrentItem();
            if(Objects.nonNull(discountRule)) {
                if (discountRule instanceof ItemDiscountRule) {
                    try {
                        itemDiscountRequestHandler.handleRequest(memberDiscountRequest);
                    }catch (UndefinedDiscountException undefinedDiscountException){
                        iterator.nextItem();
                        continue;
                    }

                } else if (discountRule instanceof PriceDiscountRule) {
                    try {
                        priceDiscountRequestSuccessor.handleRequest(memberDiscountRequest);
                    }catch (UndefinedDiscountException undefinedDiscountException){
                        iterator.nextItem();
                        continue;
                    }
                }
            }
            iterator.nextItem();
        }
    }

    /**
     * Handle a requested discount on a sale.
     * Identifies type of discount defined by
     * <code> DiscountRule </code> and sends
     * the request
     * @param memberDiscountRequest
     * @throws Exception
     */

    public void handleRequest(MemberDiscountRequest memberDiscountRequest) throws Exception{
        ListSequence<DiscountRule> ruleListSequence = memberDiscountRequest.getDiscountRuleListSequence();
        ListSequenceIterator<DiscountRule> iterator = (ListSequenceIterator<DiscountRule>) ruleListSequence.getSequenceIterator();

        iterator.firstItem();
        while (!iterator.isOver()) {
            DiscountRule discountRule = iterator.getCurrentItem();

            if(Objects.nonNull(discountRule)) {
                memberDiscountRequest.setRequestedDiscount(discountRule);
                if (discountRule instanceof ItemDiscountRule) {
                    try {
                        itemDiscountRequestHandler.handleRequest(memberDiscountRequest);
                    }catch (UndefinedDiscountException undefinedDiscountException){
                        iterator.nextItem();
                        continue;
                    }

                } else if (discountRule instanceof PriceDiscountRule) {
                    try {
                        priceDiscountRequestSuccessor.handleRequest(memberDiscountRequest);
                    }catch (UndefinedDiscountException undefinedDiscountException){
                        iterator.nextItem();
                        continue;
                    }
                }
            }
            iterator.nextItem();
        }
    }

}


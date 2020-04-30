package service.handlerpattern.discountidentifier;

import model.customer.MemberDiscountRequest;
import model.discount.discountrule.DiscountRule;
import model.discount.discountrule.itemdiscountrule.ItemDiscountRule;
import model.discount.discountrule.pricediscountrule.PriceDiscountRule;
import model.discount.discounttypes.pricediscount.PriceDiscount;
import sequence.ListSequence;
import sequence.ListSequenceIterator;
import service.handlerpattern.requesthandler.TotalCostDiscountRequestHandler;
import util.exception.UndefinedDiscountException;

import java.util.Objects;

public class DiscountRuleIdentifier extends DiscountRequestHandler {

    public DiscountRuleIdentifier(TotalCostDiscountRequestHandler successor1, ItemReqHandler successor2) {
        super(successor1, successor2);
    }

    @Override
    public void handleRequest(MemberDiscountRequest memberDiscountRequest) throws Exception{
        ListSequence<DiscountRule> ruleListSequence = memberDiscountRequest.getDiscountRuleListSequence();
        ListSequenceIterator<DiscountRule> iterator = (ListSequenceIterator<DiscountRule>) ruleListSequence.createSeqIterator();

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


package service.discountservice.discountidentifier;

import exception.ErrorId;
import exception.exceptionhandler.ExceptionHandler;
import model.customer.customerrequest.MemberDiscountRequest;
import model.discount.discountrule.DiscountRule;
import model.discount.discountrule.itemdiscountrule.ItemDiscountRule;
import model.discount.discountrule.pricediscountrule.PriceDiscountRule;
import exception.businessruleexception.BusinessLogicException;
import service.discountservice.discountRequestHandler.ItemDiscountRequestHandler;
import util.sequence.ListSequence;
import util.sequence.ListSequenceIterator;
import service.discountservice.discountRequestHandler.TotalCostDiscountRequestHandler;
import exception.businessruleexception.UndefinedDiscountException;

import java.util.Objects;

public class DiscountRuleIdentifier extends DiscountRequestHandler {

    public DiscountRuleIdentifier(TotalCostDiscountRequestHandler successor1, ItemDiscountRequestHandler successor2) {
        super(successor1, successor2);
    }

    /**
     * Handle a requested discount on a sale.
     * Identifies type of discount defined by
     * <code> DiscountRule </code> and sends
     * the request
     *
     * @param memberDiscountRequest
     * @exception Exception
     */
    @Override
    public void handle(MemberDiscountRequest memberDiscountRequest) {
        ListSequence<DiscountRule> ruleListSequence = memberDiscountRequest.getDiscountRuleListSequence();
        ListSequenceIterator<DiscountRule> iterator = (ListSequenceIterator<DiscountRule>) ruleListSequence.sequenceIterator();

        iterator.firstItem();
        while (!iterator.isOver()) {

            DiscountRule discountRule = iterator.getCurrentItem();
            if (Objects.nonNull(discountRule)) {
                if (discountRule instanceof ItemDiscountRule) {
                    try {
                        itemDiscountRequestHandler.handleRequest(memberDiscountRequest);
                    } catch (UndefinedDiscountException undefinedDiscountException) {
                        ExceptionHandler.handle(new BusinessLogicException(undefinedDiscountException, ErrorId.BUSINESS_LOGIC_ERROR));
                        iterator.nextItem();
                        continue;
                    }

                } else if (discountRule instanceof PriceDiscountRule) {
                    try {
                        priceDiscountRequestSuccessor.handleRequest(memberDiscountRequest);
                    } catch (UndefinedDiscountException undefinedDiscountException) {
                        ExceptionHandler.handle(new BusinessLogicException(undefinedDiscountException, ErrorId.BUSINESS_LOGIC_ERROR));
                        iterator.nextItem();
                        continue;
                    }
                }
            }
            iterator.nextItem();
        }
    }
}


package service.handlerpattern.requesthandler;
import model.amount.Cost;
import model.customer.MemberDiscountRequest;
import model.discount.discountrule.pricediscountrule.TotalCostDiscountRule;
import model.discount.discounttypes.pricediscount.TotalCostDiscount;
import service.handlerpattern.discountidentifier.PriceDiscountHandler;
import util.exception.UndefinedDiscountException;

import java.util.Objects;

public class TotalCostDiscountRequestHandler extends PriceDiscountHandler {

    public TotalCostDiscountRequestHandler(PriceDiscountHandler successor) {
        super(successor);
    }

    @Override
    public void handleRequest(MemberDiscountRequest memberDiscountRequest) {
        if(memberDiscountRequest.getRequestedDiscount() instanceof TotalCostDiscountRule){
            TotalCostDiscountRule costDiscountRule = (TotalCostDiscountRule) memberDiscountRequest.getRequestedDiscount();
            double minimumAmount = costDiscountRule.getMinimumSpend();
            double discountRate = costDiscountRule.getDiscountRate();
            Cost cost = memberDiscountRequest.getSaleCost();
            if( costDiscountRule.checkRequierments(cost.getTotalCost()) ){
                double totalPriceReduction = cost.getTotalCost() * discountRate;
                TotalCostDiscount totalCostDiscount = new TotalCostDiscount(costDiscountRule);
                totalCostDiscount.setTotalPriceReduction(totalPriceReduction);
                cost.setPriceDiscount(totalCostDiscount);
            }
        }

        else{
            if(Objects.nonNull(successor))
                successor.handleRequest(memberDiscountRequest);
            else throw new UndefinedDiscountException();
        }
    }
}

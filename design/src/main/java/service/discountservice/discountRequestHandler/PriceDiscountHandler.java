package service.discountservice.discountRequestHandler;

import model.customer.customerrequest.MemberDiscountRequest;
import service.visitor.Visitor;

public abstract class PriceDiscountHandler {
    protected Visitor visitor;
    protected PriceDiscountHandler successor;
    public PriceDiscountHandler(PriceDiscountHandler successor) {
        super();
        this.successor = successor;
    }

    public abstract void handleRequest(MemberDiscountRequest memberDiscountRequest);
/*
    @Override
    public void handleRequest(MemberDiscountRequest memberDiscountRequest) {
        if(memberDiscountRequest.getRequestedDiscount() instanceof PriceDiscount){
            PriceDiscountRule priceDiscountRule = (PriceDiscountRule) memberDiscountRequest.getRequestedDiscount();
            double minimumSpent = priceDiscountRule.getMinimumSpend();
            Cost cost = memberDiscountRequest.getCurrentSale().getCost();
            if(minimumSpent <= cost.getNumber()){
                TotalCostDiscount totalCostDiscount = new TotalCostDiscount(priceDiscountRule);
            }

        }

        else{
            throw new UndefinedDiscountException();
        }
    }

    */
}

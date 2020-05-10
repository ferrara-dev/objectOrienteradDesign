package service.modelservice.discountservice.discountidentifier;

import model.customer.customerrequest.MemberDiscountRequest;


public abstract class DiscountStrategyHandler {
    protected DiscountStrategyHandler successor=null;

    public DiscountStrategyHandler(DiscountStrategyHandler successor) {
        super();
        this.successor= successor;
    }

    public abstract void handleRequest(MemberDiscountRequest memberDiscountRequest);

}

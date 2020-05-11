package service.discountservice.discountRequestHandler;

import model.customer.customerrequest.MemberDiscountRequest;

public abstract class ItemDiscountRequestHandler {
    protected ItemDiscountRequestHandler successor=null;

    public ItemDiscountRequestHandler(ItemDiscountRequestHandler successor) {
        super();
        this.successor= successor;
    }

    public abstract void handleRequest(MemberDiscountRequest memberDiscountRequest);

}

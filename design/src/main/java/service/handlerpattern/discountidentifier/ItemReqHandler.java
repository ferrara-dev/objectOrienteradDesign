package service.handlerpattern.discountidentifier;

import model.customer.MemberDiscountRequest;

public abstract class ItemReqHandler {
    protected ItemReqHandler successor=null;

    public ItemReqHandler(ItemReqHandler successor) {
        super();
        this.successor= successor;
    }

    public abstract void handleRequest(MemberDiscountRequest memberDiscountRequest);

}

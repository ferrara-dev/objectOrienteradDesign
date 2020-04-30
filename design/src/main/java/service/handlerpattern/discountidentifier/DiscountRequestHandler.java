package service.handlerpattern.discountidentifier;

import model.customer.MemberDiscountRequest;
import service.handlerpattern.requesthandler.TotalCostDiscountRequestHandler;

public abstract class DiscountRequestHandler {
    protected TotalCostDiscountRequestHandler priceDiscountRequestSuccessor=null;
    protected ItemReqHandler itemDiscountRequestHandler =null;

    public DiscountRequestHandler(TotalCostDiscountRequestHandler successor1, ItemReqHandler successor2) {
        super();
        priceDiscountRequestSuccessor = successor1;
        itemDiscountRequestHandler = successor2;
    }

    public abstract void handleRequest(MemberDiscountRequest memberDiscountRequest) throws Exception;
}


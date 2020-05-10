package service.modelservice.discountservice.discountidentifier;

import model.customer.customerrequest.MemberDiscountRequest;
import service.handlerpattern.Handler;
import service.modelservice.discountservice.discountRequestHandler.ItemDiscountRequestHandler;
import service.modelservice.discountservice.discountRequestHandler.TotalCostDiscountRequestHandler;

public abstract class DiscountRequestHandler implements Handler <MemberDiscountRequest>{
    protected TotalCostDiscountRequestHandler priceDiscountRequestSuccessor = null;
    protected ItemDiscountRequestHandler itemDiscountRequestHandler = null;

    public DiscountRequestHandler(TotalCostDiscountRequestHandler successor1, ItemDiscountRequestHandler successor2) {
        super();
        priceDiscountRequestSuccessor = successor1;
        itemDiscountRequestHandler = successor2;
    }
    @Override
    public abstract void handle(MemberDiscountRequest memberDiscountRequest);
}


package service.modelservice.discountservice.discountidentifier;

import model.customer.customerrequest.MemberDiscountRequest;
import service.modelservice.discountservice.discountRequestHandler.BulkDiscountRequestHandler;
import service.modelservice.discountservice.discountRequestHandler.ItemDiscountRequestHandler;


public class BulkDiscountStrategyHandler extends DiscountStrategyHandler{

    private ItemDiscountRequestHandler itemDiscountRequestHandler;

    public BulkDiscountStrategyHandler(DiscountStrategyHandler successor) {
        super(successor);
        itemDiscountRequestHandler = new BulkDiscountRequestHandler(null);
    }

    @Override
    public void handleRequest(MemberDiscountRequest memberDiscountRequest) {

    }
}

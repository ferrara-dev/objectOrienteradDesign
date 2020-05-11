package service.discountservice.discountidentifier;

import model.customer.customerrequest.MemberDiscountRequest;
import service.discountservice.discountRequestHandler.BulkDiscountRequestHandler;
import service.discountservice.discountRequestHandler.ItemDiscountRequestHandler;


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

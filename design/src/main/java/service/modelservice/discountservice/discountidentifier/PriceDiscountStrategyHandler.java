package service.modelservice.discountservice.discountidentifier;

import model.customer.customerrequest.MemberDiscountRequest;
import service.modelservice.discountservice.discountRequestHandler.PriceDiscountHandler;
import service.modelservice.discountservice.discountRequestHandler.TotalCostDiscountRequestHandler;

public class PriceDiscountStrategyHandler extends DiscountStrategyHandler {
    private PriceDiscountHandler priceDiscountHandler;

    public PriceDiscountStrategyHandler(DiscountStrategyHandler successor) {
        super(successor);
        priceDiscountHandler = new TotalCostDiscountRequestHandler(null);
    }

    @Override
    public void handleRequest(MemberDiscountRequest memberDiscountRequest) {

    }
}

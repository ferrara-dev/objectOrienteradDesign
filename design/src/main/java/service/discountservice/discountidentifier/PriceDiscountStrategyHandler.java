package service.discountservice.discountidentifier;

import model.customer.customerrequest.MemberDiscountRequest;
import service.discountservice.discountRequestHandler.PriceDiscountHandler;
import service.discountservice.discountRequestHandler.TotalCostDiscountRequestHandler;

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

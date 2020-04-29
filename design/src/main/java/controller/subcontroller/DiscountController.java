package controller.subcontroller;

import service.modelservice.discountservice.DiscountService;

public class DiscountController {
    DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    /**
     * Inititate handlig of a discount request
     * @param customerId the id of the customer requesting a discount
     */
    public void handleDiscountRequest(String customerId) {
        discountService.initiateDiscountRequest(customerId);
        discountService.processRequest();
        discountService.applyValidDiscounts();
    }
}

package controller.subcontroller;

import service.modelservice.discountservice.DiscountService;

public class DiscountController {
    DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void handleDiscountRequest(String customerId) {
        discountService.initiateDiscountRequest(customerId);
        discountService.processRequest();
        discountService.applyValidDiscounts();
    }
}

package controller.subcontroller;

import service.modelservice.paymentservice.PaymentService;

public class PaymentController {
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    /**
     * Inititate the paymentProcess
     * @param amount the amount payed
     */
    public void initiatePaymentProcess(double amount){
        paymentService.processPayment(amount);
    }
}

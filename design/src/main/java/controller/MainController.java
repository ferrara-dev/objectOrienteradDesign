package controller;


import controller.subcontroller.CustomerController;
import controller.subcontroller.DiscountController;
import controller.subcontroller.PaymentController;
import controller.subcontroller.SaleController;
import startup.layercreator.ControllerCreator;
import observer.EventObserver;


public class MainController implements Controller{
    private CustomerController customerController;
    private DiscountController discountController;
    private SaleController saleController;
    private PaymentController paymentController;

    public MainController(ControllerCreator controllerCreator) {
        customerController = controllerCreator.getCustomerController();
        discountController = controllerCreator.getDiscountController();
        saleController = controllerCreator.getSaleController();
        paymentController = controllerCreator.getPaymentController();
    }

    public void startSale() {
        saleController.startSale();
    }

    public void registerProduct(int itemId, int quantity) {
        saleController.registerProduct(itemId, quantity);
    }

    public void requestCustomerDiscount(String customerId) {
            discountController.handleDiscountRequest(customerId);
    }

    public void endSale(){
        saleController.endSale();
    }

    public void enterPayment(double amount){
            paymentController.enterPayment(amount);
    }

    public PaymentController getPaymentController() {
        return paymentController;
    }

    public SaleController getSaleController() {
        return saleController;
    }

    public DiscountController getDiscountController() {
        return discountController;
    }

    public CustomerController getCustomerController() {
        return customerController;
    }

    @Override
    public void addObserver(EventObserver observer) {
        saleController.addObservers(observer);
    }

}

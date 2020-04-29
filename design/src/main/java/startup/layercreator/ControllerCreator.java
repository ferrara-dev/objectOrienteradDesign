package startup.layercreator;

import controller.MainController;
import controller.subcontroller.CustomerController;
import controller.subcontroller.DiscountController;
import controller.subcontroller.PaymentController;
import controller.subcontroller.SaleController;


public class ControllerCreator {
    MainController mainController;
    SaleController saleController;
    DiscountController discountController;
    CustomerController customerController;
    PaymentController paymentController;

    public ControllerCreator(ServiceCreator serviceCreator){
        paymentController = new PaymentController(serviceCreator.getPaymentService());
        saleController = new SaleController(serviceCreator.getSaleService());
        discountController = new DiscountController(serviceCreator.getDiscountService());
        customerController = new CustomerController(serviceCreator.getCustomerService());
        mainController = new MainController(this);
    }

    public PaymentController getPaymentController() {
        return paymentController;
    }

    public CustomerController getCustomerController() {
        return customerController;
    }

    public DiscountController getDiscountController() {
        return discountController;
    }

    public SaleController getSaleController() {
        return saleController;
    }

    public MainController getMainController() {
        return mainController;
    }
}

package controller;


import controller.subcontroller.CustomerController;
import controller.subcontroller.DiscountController;
import controller.subcontroller.PaymentController;
import controller.subcontroller.SaleController;
import startup.layer.ControllerCreator;
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

    /**
     * Called from the view to start a new sale
     * calls method <code> startsale() </code>
     * resided in an instance of the  <code> SaleController </code>
     */
    public void startSale() {
        saleController.startSale();
    }

    /**
     * Called from the view to register a new product.
     *
     * calls <code> SaleController </code> to initiate
     * the process of registering a product.
     *
     * The valid product identifications are 1,2,3,4,5,6,7
     * @param itemId
     * @param quantity
     */
    public void registerProduct(int itemId, int quantity) {
        saleController.initiateProductRegistration(itemId, quantity);
    }

    /**
     * Call from the view to request a discount
     *
     * calls <code> DiscountController </code>
     * to initiate handling of the request.
     * @param customerId
     */
    public void requestCustomerDiscount(String customerId) {
            discountController.handleDiscountRequest(customerId);
    }

    /**
     * Call from the view to end a sale in progress.
     */
    public void endSale(){
        saleController.endSale();
    }
    /**
     * Call from the view to enter a payment.
     *
     * calls <code> paymentController</code> to
     * initiate the payment process
     *
     */
    public void enterPayment(double amount){
            paymentController.initiatePaymentProcess(amount);
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

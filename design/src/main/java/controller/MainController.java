package controller;

import service.ServiceFacade;
import startup.layer.ControllerCreator;
import observer.EventObserver;
import util.datatransferobject.PaymentDTO;
import util.datatransferobject.SaleItemDTO;


public class MainController implements Controller{
    private ServiceFacade serviceFacade;

    public MainController(ControllerCreator controllerCreator) {

    }

    public MainController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }
    /**
     * Called from the view to start a new sale
     * calls method <code> startsale() </code>
     * resided in an instance of the  <code> SaleController </code>
     */
    public void startSale() {
        serviceFacade.startSale();
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
    public void registerProduct(SaleItemDTO saleItemDTO) {
        serviceFacade.registerProduct(saleItemDTO);
    }

    /**
     * Call from the view to request a discount
     *
     * calls <code> DiscountController </code>
     * to initiate handling of the request.
     * @param customerId
     */
    public void requestCustomerDiscount(String customerId) {
           serviceFacade.requestDiscount(customerId);
    }

    /**
     * Call from the view to end a sale in progress.
     */
    public void endSale(){
        serviceFacade.endSale();
        //saleController.endSale();
    }
    /**
     * Call from the view to enter a payment.
     *
     * calls <code> paymentController</code> to
     * initiate the payment process
     *
     */
    public void enterPayment(PaymentDTO paymentDTO){
        serviceFacade.initPayment(paymentDTO);
    }

    @Override
    public void addObserver(EventObserver observer) {

        //saleController.addObservers(observer);
    }

}

package startup.layer;

import controller.MainController;
import view.InputView;
import view.MainView;
import view.View;

import java.util.ArrayList;


public class ControllerCreator {
    MainController mainController;

    public ControllerCreator(ServiceCreator serviceCreator){
        mainController = new MainController(serviceCreator.getServiceFacade());
    }

    public void configureObservers(ArrayList<InputView> eventObservers){
        for(View view: eventObservers){
            if(view instanceof InputView){
                ((InputView) view).addController(mainController);
            }
        }
    }
/*
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
*/

    public MainController getMainController() {
        return mainController;
    }
}

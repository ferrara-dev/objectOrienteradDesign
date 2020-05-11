package startup.layer;

import factory.IntegrationFactory;
import integration.PhysicalObjectsRepository;
import observer.EventObserver;
import util.exception.notfoundexception.NotFoundException;
import view.*;
import view.closedsaleview.ClosedSaleMainView;
import view.guiutil.ExceptionView;
import view.initialview.InitialMainView;
import view.paymentview.PaymentMainView;
import view.saleview.SaleMainView;
import view.storesystemview.RevenueMainView;
import view.storesystemview.SystemView;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ViewCreator {
    private ControllerCreator controllerCreator;
    private CashierMainView view;
    private CashierDisplay cashierDisplay;
    private SystemView revenueView;
    private ExceptionView exceptionView;
    InitialMainView initialView;
    SaleMainView saleView;
    PaymentMainView paymentView;
    ClosedSaleMainView closedSaleView;

    public ViewCreator(ControllerCreator controllerCreator){
        this.controllerCreator = controllerCreator;
    }

    public ViewCreator(){

    }

    public void createView(){
        JTabbedPane tabbedPane = new JTabbedPane();
        cashierDisplay = new CashierDisplay();
        cashierDisplay.add(tabbedPane);
        revenueView = new SystemView();
        initialView = new InitialMainView();
        saleView = new SaleMainView();
        paymentView = new PaymentMainView();
        closedSaleView = new ClosedSaleMainView();
        exceptionView = new ExceptionView();
        view = new CashierMainView(this);

      //  view.initDefault(controllerCreator.getMainController());
        tabbedPane.add(view,"cashier view");
        tabbedPane.add(revenueView,"System view");
    }
    
    public CashierMainView getView(){
        return this.view;
    }

    public void showView()  throws IOException, URISyntaxException {
        cashierDisplay.setDefaultLookAndFeelDecorated(true);
        cashierDisplay.pack();
        cashierDisplay.setVisible(true);
    }

    public ArrayList<EventObserver> collectObservers(){
        ArrayList<EventObserver> eventObservers = new ArrayList<>();
        eventObservers.addAll(view.collectEventObservers());
        eventObservers.addAll(revenueView.collectEventObservers());
        return eventObservers;
    }

    public ArrayList<InputView> collectInputViews(){
        ArrayList<InputView> inputViews = new ArrayList<>();
        inputViews.add(paymentView.getPaymentViewInput());
        inputViews.add(initialView.getInitialViewInput());
        inputViews.add(saleView.getSaleInputView());
        return inputViews;
    }

    public ExceptionView getExceptionView() {
        return exceptionView;
    }

    public PaymentMainView getPaymentView() {
        return paymentView;
    }

    public InitialMainView getInitialView() {
        return initialView;
    }

    public ClosedSaleMainView getClosedSaleView() {
        return closedSaleView;
    }

    public SaleMainView getSaleView() {
        return saleView;
    }

    public ControllerCreator getControllerCreator() {
        return controllerCreator;
    }

    public CashierDisplay getCashierDisplay() {
        return cashierDisplay;
    }

    public SystemView getRevenueView() {
        return revenueView;
    }
}

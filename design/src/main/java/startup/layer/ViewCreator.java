package startup.layer;

import view.CashierDisplay;
import view.CashierView;
import view.storesystemview.RevenueView;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class ViewCreator {
    private ControllerCreator controllerCreator;
    private CashierView view;
    private CashierDisplay cashierDisplay;
    private RevenueView revenueView;
    public ViewCreator(ControllerCreator controllerCreator){
        this.controllerCreator = controllerCreator;
    }

    public void createView(){
        JTabbedPane tabbedPane = new JTabbedPane();
        cashierDisplay = new CashierDisplay();
        cashierDisplay.add(tabbedPane);
        revenueView = new RevenueView();
        view = new CashierView();
        view.initDefault(controllerCreator.getMainController());
        tabbedPane.add(view,"cashier view");
        tabbedPane.add(revenueView,"System view");
    }
    
    public CashierView getView(){
        return this.view;
    }

    public void showView()  throws IOException, URISyntaxException {
        cashierDisplay.setDefaultLookAndFeelDecorated(true);
        cashierDisplay.pack();
        cashierDisplay.setVisible(true);
    }
}

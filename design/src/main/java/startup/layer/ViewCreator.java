package startup.layer;

import view.CashierDisplay;
import view.CashierView;
import java.io.IOException;
import java.net.URISyntaxException;

public class ViewCreator {
    private ControllerCreator controllerCreator;
    private CashierView view;
    private CashierDisplay cashierDisplay;

    public ViewCreator(ControllerCreator controllerCreator){
        this.controllerCreator = controllerCreator;
    }

    public void createView(){
        cashierDisplay = new CashierDisplay();
        view = new CashierView();
        view.initDefault(controllerCreator.getMainController());
        cashierDisplay.add(view);
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

package view.saleview;

import controller.Controller;
import util.datatransferobject.CostDTO;
import model.sale.SaleItem;
import net.miginfocom.swing.MigLayout;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
import observer.StateChangeEvent;
import view.View;
import view.saleview.saleviewcomponent.CartView;
import view.saleview.saleviewcomponent.CostView;
import view.saleview.saleviewcomponent.InputView;

import java.util.List;

/**
 * Class that contains all the components that are shown
 * in the user interface during an active sale process
 * Implements the <code> EventObserver interface </code>
 * in order to receive updates about changes in the model
 * layer and update its components accordingly.
 */

public class SaleView extends View {
    public static final String CARD_CONSTRAINT = "SaleView";
    private CartView cartView;
    private InputView inputView;
    private CostView costView;
    long id = 12L;
    /**
     * Creates all the components shown during a
     * sale process
     *
     * @param
     */
    public SaleView() {
        cartView = new CartView();
        costView = new CostView();
        inputView = new InputView();
        setLayout(new MigLayout("debug"));
        add(cartView, "cell 0 0 4 2");
        add(costView, "cell  0 4 4 1");
        add(inputView, "cell 0 8 4 2");
    }

    @Override
    public void stateHasChanged(ObservedEvent observedEvent) {
        if(observedEvent instanceof PropertyChangeEvent){
            String propertyName = ((PropertyChangeEvent) observedEvent).getPropertyName();
            if(propertyName == "items"){
                cartView.getSaleItemJTable().setCard((List<SaleItem>) ((PropertyChangeEvent) observedEvent).getNewValue());
            }
        }
        else if(observedEvent instanceof StateChangeEvent) {
            CostDTO cost = (CostDTO) observedEvent.getEventSource();
            double totalCost = cost.getTotalCost();
            double totalVAT  = cost.getTotalVAT();
            costView.runningTotalHasChanged(totalCost,totalVAT);
        }
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void update(String s) {

    }

    public void initDefault(Controller controller) {
        inputView.setMainController(controller);
        controller.addObserver(this);

    }
}

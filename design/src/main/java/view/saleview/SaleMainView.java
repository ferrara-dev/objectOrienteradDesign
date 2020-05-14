package view.saleview;

import controller.Controller;
import util.datatransferobject.CostDTO;
import model.sale.SaleItem;
import net.miginfocom.swing.MigLayout;
import observer.modelobserver.ObservedEvent;
import observer.modelobserver.PropertyChangeEvent;
import view.MainView;
import view.View;
import view.saleview.saleviewcomponent.CartView;
import view.saleview.saleviewcomponent.CostView;
import view.saleview.saleviewcomponent.SaleInputView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains all the components that are shown
 * in the user interface during an active sale process
 * Implements the <code> EventObserver interface </code>
 * in order to receive updates about changes in the model
 * layer and update its components accordingly.
 */

public class SaleMainView extends MainView {
    public static final String CARD_CONSTRAINT = "SaleView";
    private CartView cartView;
    private SaleInputView saleInputView;
    private CostView costView;
    long id = 12L;
    /**
     * Creates all the components shown during a
     * sale process
     *
     * @param
     */
    public SaleMainView() {
        cartView = new CartView();
        costView = new CostView();
        saleInputView = new SaleInputView();
        setLayout(new MigLayout("debug"));
        add(cartView, "cell 0 0 4 2");
        add(costView, "cell  0 4 4 1");
        add(saleInputView, "cell 0 8 4 2");
    }

    @Override
    public void newEvent(ObservedEvent observedEvent) {
        if(observedEvent instanceof PropertyChangeEvent){
            String propertyName = observedEvent.getPropertyName();
            if(propertyName == "items"){
                cartView.getSaleItemJTable().setCard((List<SaleItem>) (observedEvent).getPropertyValue());
            }
            else if(propertyName == "costDetail"){
                CostDTO cost = (CostDTO) (observedEvent).getPropertyValue();
                double runningTotal = cost.getRunningTotal();
                double totalVAT  = cost.getTotalVAT();
                double priceDiscount = cost.getPriceDiscount();
                costView.runningTotalHasChanged(runningTotal,totalVAT, priceDiscount);
            }
            else if(propertyName == "list"){
                ArrayList<SaleItem> list = (ArrayList<SaleItem>) (observedEvent).getPropertyValue();
                cartView.cartHasChanged(list);
            }
        }
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public ArrayList<View> collectViews() {
        ArrayList<View> views = new ArrayList<>();
        views.add(saleInputView);
        return null;
    }

    @Override
    public void update(String s) {

    }

    public void initDefault(Controller controller) {
        saleInputView.setMainController(controller);

    }

    public SaleInputView getSaleInputView() {
        return saleInputView;
    }
}

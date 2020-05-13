package view.closedsaleview;



import model.amount.Change;
import net.miginfocom.swing.MigLayout;
import observer.modelobserver.ObservedEvent;
import observer.modelobserver.PropertyChangeEvent;
import view.MainView;
import view.View;
import view.closedsaleview.closedsaleviewcomponents.ReceiptView;

import java.util.ArrayList;

public class ClosedSaleMainView extends MainView {
    public static final String CARD_CONSTRAINT = "ClosedSaleView";
    private ReceiptView receiptView;
    public ClosedSaleMainView(){
        setLayout(new MigLayout());
        receiptView = new ReceiptView();
       add(receiptView,"push,grow");
    }

    @Override
    public void newEvent(ObservedEvent observedEvent) {
        if(observedEvent instanceof PropertyChangeEvent){
            String propertyName = (observedEvent).getPropertyName();
            if(propertyName == "change"){
                Change change = (Change) observedEvent.getPropertyValue();
                double cashBack = change.getNumber().doubleValue();
                receiptView.update(cashBack);
            }

        }
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public ArrayList<View> collectViews() {
        return null;
    }

    @Override
    public void update(String s) {

    }
}

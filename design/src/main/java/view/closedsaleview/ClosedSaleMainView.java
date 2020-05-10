package view.closedsaleview;



import net.miginfocom.swing.MigLayout;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
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
            String propertyName = ((PropertyChangeEvent) observedEvent).getPropertyName();
            if(propertyName == "cashBack"){
                receiptView.update((Double) ((PropertyChangeEvent) observedEvent).getNewValue());
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

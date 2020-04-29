package view.closedsaleview;



import controller.MainController;
import net.miginfocom.swing.MigLayout;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
import util.datatransferobject.CostDTO;
import view.closedsaleview.closedsaleviewcomponents.ReceiptView;

import javax.swing.*;

public class ClosedSaleView extends JPanel implements EventObserver {
    public static final String CARD_CONSTRAINT = "ClosedSaleView";
    private ReceiptView receiptView;
    public ClosedSaleView(){
        setLayout(new MigLayout());
        receiptView = new ReceiptView();
       add(receiptView,"push,grow");
    }

    public void initDefault(MainController mainController){
        mainController.addObserver(this);
    }


    @Override
    public void stateHasChanged(ObservedEvent observedEvent) {
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
}

package view.paymentview;

import controller.MainController;
import util.datatransferobject.CostDTO;
import net.miginfocom.swing.MigLayout;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
import observer.StateChangeEvent;
import view.paymentview.paymentviewcomponents.PaymentViewInput;
import view.paymentview.paymentviewcomponents.TotalCostView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentView extends JPanel implements EventObserver {
    private long id = 13L;
    private TotalCostView totalCostView;
    private PaymentViewInput paymentViewInput;
    public static final String CARD_CONSTRAINT = "PaymentView";

    public PaymentView(){
        setLayout(new MigLayout("debug"));
        totalCostView = new TotalCostView();
        paymentViewInput = new PaymentViewInput();
        add(totalCostView,"cell 0 0, pushx , grow");
        add(paymentViewInput,"cell 0 1, pushx , grow");

    }

    public void initDefault(MainController mainController){
        paymentViewInput.getEnterPaymentButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == paymentViewInput.getEnterPaymentButton()){
                    mainController.enterPayment(paymentViewInput.getText());
                }
            }
        });
        mainController.addObserver(this);
    }
    @Override
    public void stateHasChanged(ObservedEvent observedEvent) {
        if(observedEvent instanceof StateChangeEvent){
            if(observedEvent.getEventSource() instanceof CostDTO) {
                StateChangeEvent stateChangeEvent = (StateChangeEvent) observedEvent;
                CostDTO cost = (CostDTO) stateChangeEvent.getEventSource();
                totalCostView.update(cost);
            }
        }

        else if(observedEvent instanceof PropertyChangeEvent){
            String propertyName =( (PropertyChangeEvent) observedEvent).getPropertyName();
            if(propertyName.equals("toPay")){
                Double toPay = (Double) ((PropertyChangeEvent) observedEvent).getNewValue();
                totalCostView.setToPayField(toPay);
            }
        }
    }

    @Override
    public long getId() {
        return id;
    }
}

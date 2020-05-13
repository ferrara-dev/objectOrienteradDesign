package view.paymentview;

import util.datatransferobject.CostDTO;
import net.miginfocom.swing.MigLayout;
import observer.modelobserver.ObservedEvent;
import observer.modelobserver.PropertyChangeEvent;
import view.MainView;
import view.View;
import view.paymentview.paymentviewcomponents.PaymentViewInput;
import view.paymentview.paymentviewcomponents.TotalCostView;

import java.util.ArrayList;

public class PaymentMainView extends MainView {
    private long id = 13L;
    private TotalCostView totalCostView;
    private PaymentViewInput paymentViewInput;
    public static final String CARD_CONSTRAINT = "PaymentView";

    public PaymentMainView(){
        setLayout(new MigLayout("debug"));
        totalCostView = new TotalCostView();
        paymentViewInput = new PaymentViewInput();
        add(totalCostView,"cell 0 0, pushx , grow");
        add(paymentViewInput,"cell 0 1, pushx , grow");

    }

    @Override
    public void newEvent(ObservedEvent observedEvent) {
        if(observedEvent instanceof PropertyChangeEvent){
            if(((PropertyChangeEvent) observedEvent).getPropertyValue() instanceof CostDTO) {
                CostDTO cost = (CostDTO) ((PropertyChangeEvent) observedEvent).getPropertyValue();
                totalCostView.update(cost);
            }
        }

        else if(observedEvent instanceof PropertyChangeEvent){
            String propertyName =( (PropertyChangeEvent) observedEvent).getPropertyName();
            if(propertyName.equals("costDetail")){
                double toPay = (Double) ((PropertyChangeEvent) observedEvent).getPropertyValue();
                totalCostView.setToPayField(toPay);
            }
        }
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public ArrayList<View> collectViews() {
        return null;
    }

    @Override
    public void update(String s) {

    }

    public PaymentViewInput getPaymentViewInput() {
        return paymentViewInput;
    }
}

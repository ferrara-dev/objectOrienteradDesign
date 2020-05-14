package view.storesystemview;

import model.amount.Balance;
import net.miginfocom.swing.MigLayout;
import observer.modelobserver.ObservedEvent;
import observer.modelobserver.PropertyChangeEvent;
import view.MainView;
import view.View;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class RevenueMainView extends MainView{
    private JFormattedTextField registerBalanceField;
    private NumberFormat paymentFormat;

    public RevenueMainView (){
        setLayout(new MigLayout("fill"));
        paymentFormat = NumberFormat.getCurrencyInstance();
        paymentFormat.setMaximumFractionDigits(2);
        registerBalanceField = new JFormattedTextField(paymentFormat);
        registerBalanceField = new JFormattedTextField(0);
        add(registerBalanceField);
    }

    @Override
    public ArrayList<View> collectViews() {
        return null;
    }

    @Override
    public void update(String s) {

    }

    @Override
    public void newEvent(ObservedEvent observedEvent) {
        if(observedEvent instanceof PropertyChangeEvent){
            if(((PropertyChangeEvent) observedEvent).getPropertyName() == "balance"){
               Balance balance = (Balance) ((PropertyChangeEvent) observedEvent).getPropertyValue();
               double b = balance.getNumber().doubleValue();
               registerBalanceField.setValue(b);
            }
        }
    }

    @Override
    public long getId() {
        return 0;
    }
}

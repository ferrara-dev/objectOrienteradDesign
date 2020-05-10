package view.storesystemview;

import model.banking.Balance;
import net.miginfocom.swing.MigLayout;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
import view.MainView;
import view.View;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class RevenueMainView extends MainView{
    private JPanel SystemViewPanel;
    private JTabbedPane tabbedPane1;

    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JFormattedTextField memberServices;
    private JFormattedTextField formattedTextField4;
    private JButton applyForMembershipButton;

    private JLabel ActiveAccounts;
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
               Balance balance = (Balance) ((PropertyChangeEvent) observedEvent).getNewValue();
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

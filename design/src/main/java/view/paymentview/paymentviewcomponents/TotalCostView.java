package view.paymentview.paymentviewcomponents;

import util.datatransferobject.CostDTO;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.text.NumberFormat;

public class TotalCostView extends JPanel {
    //Labels
    private final JLabel totalCostLabel;
    private final JLabel totalVatLabel;
    private final JLabel totalDiscountLabel;
    private final JLabel toPayLabel;
    //Strings for the labels
    private static final String totalCost = "Total Cost :  ";
    private static final String totalVAT = "Total VAT :  ";
    private static final String totalDiscount = "Total Discount :  ";
    private static final String toPay = "To pay :  ";

    private JFormattedTextField totalCostField;
    private JFormattedTextField totalVatField;
    private JFormattedTextField totalDiscountField;
    private JFormattedTextField toPayField;


    //Formats to format and parse numbers
    private NumberFormat amountFormat;
    private NumberFormat percentFormat;
    private NumberFormat paymentFormat;

    public TotalCostView() {
        setLayout(new MigLayout("fill"));

        totalCostLabel = new JLabel(totalCost);
        totalDiscountLabel = new JLabel(totalDiscount);
        totalVatLabel = new JLabel(totalVAT);
        toPayLabel = new JLabel(toPay);

        setUpFormats();
        addComponents();
    }

    public void update(CostDTO costDTO){
        totalCostField.setValue(costDTO.getFinalCost());
        totalVatField.setValue(costDTO.getTotalVAT());
        toPayField.setValue(costDTO.getRunningTotal());
        super.revalidate();
    }

    public void setToPayField(double toPay) {
       toPayField.setValue(toPay);
       toPayField.revalidate();
    }


    private void addComponents(){
        add(totalCostLabel);
        add(totalCostField, "wrap");
        add(totalVatLabel);
        add(totalVatField,"wrap");
        add(totalDiscountLabel);
        add(totalDiscountField,"wrap");
        add(toPayLabel);
        add(toPayField,"wrap");
    }

    private void setUpFormats() {
        paymentFormat = NumberFormat.getCurrencyInstance();
        paymentFormat.setMaximumFractionDigits(2);
        toPayField = new JFormattedTextField(paymentFormat);
        toPayField.setEditable(false);
        toPayField.setColumns(20);
        totalDiscountField = new JFormattedTextField(paymentFormat);
        totalDiscountField.setEditable(false);
        totalDiscountField.setColumns(20);
        totalVatField = new JFormattedTextField(paymentFormat);
        totalVatField.setEditable(false);
        totalVatField.setColumns(20);
        totalCostField = new JFormattedTextField(paymentFormat);
        totalCostField.setEditable(false);
        totalCostField.setColumns(20);
    }


}
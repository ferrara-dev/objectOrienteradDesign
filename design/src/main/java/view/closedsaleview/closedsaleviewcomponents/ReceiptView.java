package view.closedsaleview.closedsaleviewcomponents;

import net.miginfocom.swing.MigLayout;
import util.datatransferobject.CostDTO;

import javax.swing.*;
import java.text.NumberFormat;

public class ReceiptView extends JPanel {
    //Labels
    private final JLabel totalChangeLabel;

    //Strings for the labels
    private static final String change = "Change ";
    private JFormattedTextField changeField;

    //Formats to format and parse numbers
    private NumberFormat paymentFormat;

    public ReceiptView() {
        setLayout(new MigLayout("fill"));
        totalChangeLabel = new JLabel(change);
        setUpFormats();
        addComponents();

    }

    public void update(double change){
        changeField.setValue(change);
        super.revalidate();
    }



    private void addComponents(){
        add(totalChangeLabel);
        add(changeField);
    }

    private void setUpFormats() {
        paymentFormat = NumberFormat.getCurrencyInstance();
        paymentFormat.setMaximumFractionDigits(2);
        changeField = new JFormattedTextField(paymentFormat);
        changeField.setEditable(false);
        changeField.setColumns(20);
    }

}

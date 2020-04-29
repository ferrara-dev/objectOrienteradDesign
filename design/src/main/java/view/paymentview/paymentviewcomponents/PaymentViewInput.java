package view.paymentview.paymentviewcomponents;
import util.datatransferobject.CostDTO;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class PaymentViewInput extends JPanel {
    private JButton enterPaymentButton;
    private JTextField paymentInputField;

    public PaymentViewInput() {
        setLayout(new MigLayout());
        enterPaymentButton = new JButton("Enter payment");
        paymentInputField = new JTextField(10);
        add(enterPaymentButton);
        add(paymentInputField);
    }

    public JButton getEnterPaymentButton() {
        return enterPaymentButton;
    }

    public double getText() {
        double paymentInput = 0D;
        if(!paymentInputField.getText().isEmpty()) {
           paymentInput = Double.parseDouble(paymentInputField.getText());
        }
        return paymentInput;
    }
}

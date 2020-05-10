package view.paymentview.paymentviewcomponents;

import controller.MainController;
import net.miginfocom.swing.MigLayout;
import util.datatransferobject.PaymentDTO;
import view.InputView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentViewInput extends InputView {
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
        if (!paymentInputField.getText().isEmpty()) {
            paymentInput = Double.parseDouble(paymentInputField.getText());
        }
        return paymentInput;
    }

    @Override
    public void addController(MainController controller) {
        enterPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.enterPayment(new PaymentDTO(getText(),"Cash Payment"));
            }
        });
    }

}

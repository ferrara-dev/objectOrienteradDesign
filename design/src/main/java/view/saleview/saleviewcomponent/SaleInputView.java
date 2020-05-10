package view.saleview.saleviewcomponent;
import controller.Controller;
import controller.MainController;
import net.miginfocom.swing.MigLayout;
import util.datatransferobject.SaleItemDTO;
import util.exception.notfoundexception.NotFoundException;
import view.InputView;
import view.guiutil.GuiErrorMessage;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleInputView extends InputView {

    InputButtonPanel inputButtonPanel;
    InputTextFieldPanel inputTextFieldPanel;
    private MainController mainController;
    public SaleInputView(){
        setLayout(new MigLayout("fill"));
        inputButtonPanel = new InputButtonPanel();
        inputTextFieldPanel = new InputTextFieldPanel();
        add(inputButtonPanel,"south ,grow");
        add(inputTextFieldPanel,"south ,grow");
    }

    public void setMainController(Controller mainController) {
        this.mainController = (MainController) mainController;
        setLayout(new MigLayout("fill"));
        inputButtonPanel = new InputButtonPanel();
        inputTextFieldPanel = new InputTextFieldPanel();
        add(inputButtonPanel,"south ,grow");
        add(inputTextFieldPanel,"south ,grow");
    }

    @Override
    public void addController(MainController controller) {
        inputButtonPanel.registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == inputButtonPanel.registrationButton) {
                    try {
                        int itemId = inputTextFieldPanel.getItemIdTextFieldInput();
                        int quantity = inputTextFieldPanel.getItemQuantityTextFieldInput();
                            controller.registerProduct(new SaleItemDTO(itemId,quantity));
                    } catch (NumberFormatException ex) {

                    } catch (IllegalStateException ex1) {

                    }
                }
            }
        });

        inputButtonPanel.endSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == inputButtonPanel.endSaleButton) {
                    controller.endSale();
                }
            }
        });

        inputButtonPanel.enterCustomerIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == inputButtonPanel.enterCustomerIdButton) {
                        try{
                            String customerId = inputTextFieldPanel.getCustomerIdFieldInput();
                            controller.requestCustomerDiscount(customerId);
                        }
                        catch (IllegalStateException el){

                        }
                }

            }
        });
    }


    private class InputTextFieldPanel extends JPanel {
        private JLabel customerIdLabel = new JLabel("Customer id :");
        private JLabel itemIdLabel = new JLabel("Item id :");
        private JLabel itemQuantityLabel = new JLabel("Quantity");

        private JTextField customerIdTextField;
        private JTextField itemIdTextField;
        private JTextField itemQuantity;

        public InputTextFieldPanel() {
            setLayout(new MigLayout("fill"));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            customerIdLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            itemIdLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            itemQuantityLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

            customerIdTextField = new JTextField(20);
            itemIdTextField = new JTextField(20);
            itemQuantity = new JTextField(20);

            add(customerIdLabel, "grow");
            add(customerIdTextField, "grow, wrap");
            add(itemIdLabel, "grow");
            add(itemIdTextField, "grow, wrap");
            add(itemQuantityLabel, "grow");
            add(itemQuantity, "grow,wrap");
        }

        public int getItemQuantityTextFieldInput() {
            if (itemQuantity.getText().isEmpty())
                return 1;
            else
                return Integer.parseInt(itemQuantity.getText());
        }

        public int getItemIdTextFieldInput() {
            if (itemIdTextField.getText().isEmpty())
                throw new IllegalStateException();

            return Integer.parseInt(itemIdTextField.getText());

        }

        private String getCustomerIdFieldInput() {
            if (customerIdTextField.getText().isEmpty()) {
                throw new IllegalStateException();
            }
            return customerIdTextField.getText();
        }
    }

    private class InputButtonPanel extends JPanel {
        private JButton registrationButton;
        private JButton endSaleButton;
        private JButton enterCustomerIdButton;

        public InputButtonPanel() {
            setLayout(new MigLayout("fill"));
            registrationButton = new JButton("Register item");
            registrationButton.setFont(new Font("Arial", Font.BOLD, 14));
            registrationButton.setBackground(Color.GREEN);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            endSaleButton = new JButton("End sale");
            endSaleButton.setFont(new Font("Arial", Font.BOLD, 14));
            endSaleButton.setBackground(Color.RED);

            enterCustomerIdButton = new JButton("Enter customer Id");
            enterCustomerIdButton.setFont(new Font("Arial", Font.BOLD, 14));
            enterCustomerIdButton.setBackground(Color.orange);

            add(enterCustomerIdButton, "south, w 60");
            add(endSaleButton, "south, w 60 ");
            add(registrationButton, "south, w 60 ");
        }
    }

}

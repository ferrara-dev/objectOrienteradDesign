package view.guiutil;


import observer.ExceptionEvent;
import util.exception.ErrorId;
import view.ExceptionListener;

import javax.swing.*;


public class ExceptionView extends JOptionPane implements ExceptionListener {


    public ExceptionView() {

    }

    @Override
    public void showException(ExceptionEvent exceptionEvent) {
        ErrorId errorId = exceptionEvent.getErrorId();
        switch (errorId) {
            case CUSTOMER_ID_NOT_FOUND: {
                createInvalidCustomerIdMessage(exceptionEvent);
                break;
            }
            case PRODUCT_ID_NOT_FOUND: {
                createInvalidProductIdMessage(exceptionEvent);
            }
        }
    }

    private void createInvalidProductIdMessage(ExceptionEvent exceptionEvent) {
        String errorMessage = "Product with  "
                + exceptionEvent.getInformation()
                + " not found";
        showMessageDialog(null, errorMessage, "Invalid product ID",
                JOptionPane.ERROR_MESSAGE);
    }

    private void createInvalidCustomerIdMessage(ExceptionEvent exceptionEvent) {
        String errorMessage = "Customer with  "
                + exceptionEvent.getInformation()
                + " not found";
        showMessageDialog(null, errorMessage, "Invalid member ID",
                JOptionPane.ERROR_MESSAGE);
    }
}

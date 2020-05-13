package view.exceptionview;


import observer.exceptionobserver.ExceptionEvent;
import exception.DataBaseAccessFailureException;
import exception.ErrorId;
import observer.exceptionobserver.ExceptionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;

/**
 * This class implements the <code> ExceptionListener </code> interface and
 * is responsible for creating and displaying errorMessages to the user.
 */

public class ExceptionView extends JOptionPane implements ExceptionListener {

    public ExceptionView() {

    }

    /**
     * This method is called from on of the <code> ExceptionHandlingChain </code>
     * subclasses to notify the user that some kind of problem has occurred.
     *
     * @param exceptionEvent containing information about the
     *                       exception that was thrown in the
     *                       program.
     */
    @Override
    public void exceptionThrown(ExceptionEvent exceptionEvent) {
        ErrorId errorId = exceptionEvent.getErrorId();
        switch (errorId) {
            case CUSTOMER_ID_NOT_FOUND: {
                createInvalidCustomerIdMessage();
                break;
            }
            case PRODUCT_ID_NOT_FOUND: {
                createInvalidProductIdMessage();
                break;
            }
            case DATABASE_ACCESS_FAILURE: {
                DataBaseAccessFailureException dataBaseAccessFailureException = (DataBaseAccessFailureException) exceptionEvent.getCause();
                boolean handled = dataBaseAccessFailureException.getFixed();
                createDataBaseAccessFailureMessage(handled);
                break;
            }
            case UNDEFINED_PROBLEM: {
                createExitMessage();
                break;
            }
            case BUSINESS_LOGIC_ERROR:{
                createBusinessLogicErrorMessage();
            }
        }
    }

    private void createBusinessLogicErrorMessage(){
                    String errorMessage =
                            "Something went wrong, please contact IT-service";
                    showMessageDialog(null, errorMessage, "ERROR",
                            JOptionPane.ERROR_MESSAGE);
    }

    JDialog jDialog = createDialog("ERROR");
    private void createExitMessage(){
        String errorMessage =
                "A problem has occurred the program will shut down";
        showMessageDialog(null, errorMessage, "ERROR",
                JOptionPane.ERROR_MESSAGE);

    }

    private void createInvalidProductIdMessage() {
        String errorMessage =
                "Product with  "
                        + "the specified ID was "
                        + " not found";
        showMessageDialog(null, errorMessage, "Invalid product ID",
                JOptionPane.ERROR_MESSAGE);
    }

    private void createInvalidCustomerIdMessage() {
        String errorMessage =
                "Customer with  "
                        + "the specified ID was "
                        + " not found";
        showMessageDialog(null, errorMessage, "Invalid member ID",
                JOptionPane.ERROR_MESSAGE);
    }

    private void createDataBaseAccessFailureMessage(boolean handled) {
        String errorMessage;
        if (handled) {
            errorMessage = "Connection problems interrupted process, please try again";
        } else
            errorMessage = "Unable to connect to database \n" +
                    "Please restart the system and contact IT-service if the problem persist";

        showMessageDialog(null, errorMessage, "Connection problem",
                JOptionPane.ERROR_MESSAGE);
    }

}

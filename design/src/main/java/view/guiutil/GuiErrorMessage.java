package view.guiutil;

import javax.swing.*;

public class GuiErrorMessage {
    public static final String ITEM_NOT_FOUND_MESSAGE = "ITEM NOT FOUND \n" +
            " please try again";
    public static final String CUSTOMER_NOT_FOUND_MESSAGE =  "CUSTOMER ID NOT FOUND \n " +
            "The id that has been entered can not be found in the database " +
            "\n please try again";
    public static final String PAYMENT_INPUT_WRONG_FORMAT = "WRONG FORMAT IN PAYMENT SECTION \n" +
            "please enter a numerical value";
    public static final String ITEM_INPUT_WRONG_FORMAT = "\"WRONG FORMAT IN ITEM ID SECTION \n" +
            " please enter an integer";

    public static void getErrorPopUp(String errorMessage){
       JOptionPane.showMessageDialog(null, errorMessage, "Error message",
                JOptionPane.ERROR_MESSAGE);
    }

}

package view.exceptionview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class DataBaseAccessErrorMessage extends JOptionPane implements ActionListener, PropertyChangeListener {
    private static final int TIME_OUT = 3;
    private int count = TIME_OUT;
    private final Timer timer = new Timer(1000, this);
    private JDialog dialog = new JDialog();
    private final JOptionPane optPane = new JOptionPane();

    public DataBaseAccessErrorMessage(){

    }

    public static void showMessage() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DataBaseAccessErrorMessage dataBaseAccessErrorMessage = new DataBaseAccessErrorMessage();

            }
        });
    }

    public JDialog getDialog() {
        return dialog;
    }



    public void actionPerformed(ActionEvent e) {
        count--;
        setMessage(message());
        if (count == 0) {
            thatsAllFolks();
        }
        timer.restart();
    }

    private String message() {
        return "A problem has occurred the program will shut down in " + count + " seconds.";
    }

    private void thatsAllFolks() {
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(
                dialog, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}


package view.initialview.initialviewcomponents;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InitialViewInput extends JPanel{
    private JButton startSaleButton;

    public InitialViewInput () {
        setLayout(new MigLayout("debug"));
        startSaleButton = new JButton("Start sale");
        add(startSaleButton,"push,grow");
    }

    public JButton getStartSaleButton() {
        return startSaleButton;
    }
}

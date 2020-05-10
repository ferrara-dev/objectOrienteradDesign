package view.initialview.initialviewcomponents;

import controller.MainController;
import net.miginfocom.swing.MigLayout;
import view.InputView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class InitialViewInput extends InputView {
    private JButton startSaleButton;

    public InitialViewInput () {
        setLayout(new MigLayout("debug"));
        startSaleButton = new JButton("Start sale");
        add(startSaleButton,"push,grow");
    }

    public JButton getStartSaleButton() {
        return startSaleButton;
    }

    @Override
    public void addController(MainController controller) {
        startSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == startSaleButton){
                    controller.startSale();
                }
            }
        });
    }
}

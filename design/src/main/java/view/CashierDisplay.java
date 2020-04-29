package view;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class CashierDisplay extends JFrame{
    private final String TITLE = "Point of Sale";
    private final Dimension SCREEN_SIZE = new Dimension(800, 600);
    private static Map<String,View> viewMap;

    public CashierDisplay(){
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        setPreferredSize(SCREEN_SIZE);
    }

    @Override
    public void add(Component comp, Object constraints) {
        super.add(comp, constraints);
    }

}

package view;

import controller.MainController;

import javax.swing.*;

public abstract class InputView extends JPanel implements View{
    public abstract void addController(MainController controller);
}

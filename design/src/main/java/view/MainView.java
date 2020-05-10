package view;
import observer.EventObserver;
import javax.swing.*;
import java.util.ArrayList;


public abstract class MainView extends JPanel implements EventObserver,View {
   public abstract ArrayList<View> collectViews();
   public abstract void update(String s);
}

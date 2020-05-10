package view.initialview;

import net.miginfocom.swing.MigLayout;
import observer.ObservedEvent;
import view.MainView;
import view.View;
import view.initialview.initialviewcomponents.InitialViewInput;

import java.util.ArrayList;


public class InitialMainView extends MainView {
    public static final String CARD_CONSTRAINT = "InitialView";
    InitialViewInput initialViewInput;
    long id = 11L;
    public InitialMainView(){
        setLayout(new MigLayout("debug"));
        initialViewInput = new InitialViewInput();
        add(initialViewInput,"push,grow");
    }

    @Override
    public void update(String s) {

    }

    @Override
    public void newEvent(ObservedEvent observedEvent) {

    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public ArrayList<View> collectViews() {
        ArrayList<View> views = new ArrayList<>();
        views.add(initialViewInput);
        return null;
    }

    public InitialViewInput getInitialViewInput() {
        return initialViewInput;
    }
}

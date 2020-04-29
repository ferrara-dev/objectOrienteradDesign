package view.initialview;

import controller.MainController;
import net.miginfocom.swing.MigLayout;
import observer.ObservedEvent;
import view.View;
import view.initialview.initialviewcomponents.InitialViewInput;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InitialView extends View {
    public static final String CARD_CONSTRAINT = "InitialView";
    InitialViewInput initialViewInput;
    long id = 11L;
    public InitialView(){
        setLayout(new MigLayout("debug"));
        initialViewInput = new InitialViewInput();
        add(initialViewInput,"push,grow");
    }

    public void initDefault(MainController mainController) {
        super.addController(mainController);
        initialViewInput.getStartSaleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == initialViewInput.getStartSaleButton()){
                    mainController.startSale();
                }
            }
        });
        mainController.addObserver(this);
    }

    @Override
    public void update(String s) {

    }

    @Override
    public void stateHasChanged(ObservedEvent observedEvent) {

    }

    @Override
    public long getId() {
        return id;
    }
}

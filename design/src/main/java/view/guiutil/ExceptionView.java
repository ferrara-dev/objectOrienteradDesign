package view.guiutil;


import observer.EventObserver;
import observer.ExceptionEvent;
import observer.ExceptionObserver;
import model.exception.UserException;
import observer.ObservedEvent;
import view.MainView;


import javax.swing.*;

public class ExceptionView extends JOptionPane implements EventObserver {
    private static ExceptionView instance;


    public ExceptionView() {

    }

    @Override
    public void newEvent(ObservedEvent observedEvent) {
        if(observedEvent instanceof ExceptionEvent)
            if(observedEvent.getEventSource() instanceof UserException)
                showMessageDialog(null, ((UserException) observedEvent.getEventSource()).getMessage(), "Error message",
                        JOptionPane.ERROR_MESSAGE);

    }

    @Override
    public long getId() {
        return 0;
    }
}

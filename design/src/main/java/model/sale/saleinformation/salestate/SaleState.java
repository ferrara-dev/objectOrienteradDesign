package model.sale.saleinformation.salestate;

import model.ObservableModel;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.StateChange;
import model.Element;
import service.visitor.Visitor;
import util.sequence.ListSequence;
import util.sequence.SequenceIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SaleState implements ObservableModel, Element {
    private List<EventObserver> eventObservers;
    private SequenceIterator<State> stateIterator;
    private State currentState;

    public void init(List<State> states) {
        eventObservers = new ArrayList<>();
        ListSequence<State> saleStates = new ListSequence<>();
        saleStates.setItems(states);
        stateIterator = saleStates.getSequenceIterator();
        currentState = saleStates.getSequenceIterator().getCurrentItem();
    }

    public void nextState() {
        if (Objects.nonNull(stateIterator)) {
            if (!stateIterator.isOver())
                stateIterator.nextItem();
            currentState = stateIterator.getCurrentItem();
            notifyObservers(new StateChange(currentState));
        }
    }

    public State getCurrentState() {
        return currentState;
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {
        for (EventObserver eventObserver : eventObservers)
            eventObserver.newEvent(observedEvent);
    }

    @Override
    public void addObserver(EventObserver eventObserver) {
        eventObservers.add(eventObserver);
    }

    @Override
    public void removeObserver(EventObserver eventObserver) {

    }

    @Override
    public void acceptVisitor(Visitor visitor){
        visitor.processElement(this);
    }
}

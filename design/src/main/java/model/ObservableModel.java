package model;

import observer.EventObserver;
import observer.ObservedEvent;

public interface ObservableModel {
    void notifyObservers(ObservedEvent observedEvent);
    void addObserver(EventObserver eventObserver);
    void removeObserver(EventObserver eventObserver);
}

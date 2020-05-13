package model;

import observer.modelobserver.EventObserver;
import observer.modelobserver.ObservedEvent;

public interface ObservableModel {
    void notifyObservers(ObservedEvent observedEvent);
    void addObserver(EventObserver eventObserver);
    void removeObserver(EventObserver eventObserver);
}

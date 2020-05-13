package controller;

import observer.modelobserver.EventObserver;

public interface Controller {
    void addObserver(EventObserver observer);
}

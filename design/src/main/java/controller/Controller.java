package controller;

import observer.EventObserver;

public interface Controller {
    void addObserver(EventObserver observer);
}

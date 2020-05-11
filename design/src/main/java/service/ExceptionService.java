package service;

import observer.EventObserver;
import observer.ExceptionEvent;


import java.util.ArrayList;

public class ExceptionService {
    private ArrayList<EventObserver> eventObservers;

    public ExceptionService(){
        eventObservers = new ArrayList<>();
    }

    public void setEventObservers(ArrayList<EventObserver> eventObservers){
        this.eventObservers.addAll(eventObservers);
    }

    public void addObserver(EventObserver eventObserver){
        eventObservers.add(eventObserver);
    }

    public void handleException(RuntimeException exception){

    }

}

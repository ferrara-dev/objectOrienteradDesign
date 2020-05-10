package service;

import model.exception.BusinessException;
import model.exception.UserException;
import observer.EventObserver;
import observer.ExceptionEvent;
import service.handlerpattern.exceptionhandler.ExceptionHandler;
import service.handlerpattern.exceptionhandler.ProductNotFoundHandler;
import service.handlerpattern.exceptionhandler.RegisterUpdateFailureHandler;

import java.util.ArrayList;

public class ExceptionService {
    private ArrayList<EventObserver> eventObservers;
    private ExceptionHandler exceptionHandler;

    public ExceptionService(){
        eventObservers = new ArrayList<>();
        exceptionHandler = new ProductNotFoundHandler(new RegisterUpdateFailureHandler(null));
    }

    public void setEventObservers(ArrayList<EventObserver> eventObservers){
        this.eventObservers.addAll(eventObservers);
    }

    public void addObserver(EventObserver eventObserver){
        eventObservers.add(eventObserver);
    }

    public void handleException(RuntimeException exception){
        try{
            exceptionHandler.handle(exception);
        } catch (UserException | BusinessException e) {
            notifyObservers(e);
        }
    }

    private void notifyObservers(Exception e){
        for(EventObserver eventObserver: eventObservers)
            eventObserver.newEvent(new ExceptionEvent(e));
    }
}

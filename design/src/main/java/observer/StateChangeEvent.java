package observer;

public class StateChangeEvent extends ObservedEvent {

    public StateChangeEvent(Object object){
        setEventSource(object);
    }
}

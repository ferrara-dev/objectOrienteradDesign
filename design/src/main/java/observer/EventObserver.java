package observer;


public interface EventObserver {
    void newEvent(ObservedEvent observedEvent);
    long getId();
}

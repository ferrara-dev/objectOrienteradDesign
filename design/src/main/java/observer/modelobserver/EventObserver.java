package observer.modelobserver;


public interface EventObserver {
    void newEvent(ObservedEvent observedEvent);
    long getId();
}

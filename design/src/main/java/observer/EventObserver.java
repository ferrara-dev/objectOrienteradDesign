package observer;


public interface EventObserver  {
    void stateHasChanged(ObservedEvent observedEvent);
    long getId();
}

package observer.modelobserver;


public interface ObservedEvent {


     Object getEventSource();
     Object getPropertyValue();
     String getPropertyName();


}

package observer;

public class PropertyChangeEvent extends ObservedEvent{

    public PropertyChangeEvent(String property, Object newValue, Object oldValue){
        setEventSource(property);
        setNewValue(newValue);
        setOldValue(oldValue);
    }

    public Object getNewValue(){
        return super.newValue;
    }

    public Object getOldValue(){
        return super.oldValue;
    }

    public String getPropertyName(){
        return (String) super.eventSource;
    }
}

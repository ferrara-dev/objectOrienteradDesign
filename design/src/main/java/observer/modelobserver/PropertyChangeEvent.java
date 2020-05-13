package observer.modelobserver;

public class PropertyChangeEvent implements ObservedEvent {
    private final Object newValue;
    private final String propertyName;

    public PropertyChangeEvent(String property, Object newValue, Object oldValue){
        this.newValue = newValue;
        this.propertyName = property;
    }

    @Override
    public Object getPropertyValue(){
        return newValue;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public Object getEventSource() {
        return newValue;
    }
}

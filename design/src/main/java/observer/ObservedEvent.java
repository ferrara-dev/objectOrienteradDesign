package observer;


public abstract class ObservedEvent {
    protected Object eventSource;
    protected Object eventCause;
    protected Object oldValue;
    protected Object newValue;

    public Object getEventSource() {
        return eventSource;
    }

    public void setEventCause(Object eventCause) {
        this.eventCause = eventCause;
    }

    public void setEventSource(Object eventSource) {
        this.eventSource = eventSource;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }


}

package observer;

import model.exception.UserException;

public class ExceptionEvent extends ObservedEvent {

    public ExceptionEvent(Exception userException) {
        super.setEventSource(userException);
    }

    @Override
    public Object getEventSource() {
        return super.getEventSource();
    }
}

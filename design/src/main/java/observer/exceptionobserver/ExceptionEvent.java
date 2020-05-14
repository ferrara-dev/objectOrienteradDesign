package observer.exceptionobserver;

import exception.ErrorId;

/**
 * Event containing information about an exception that has been thrown.
 *
 * This event is sent as parameter to an implementation of <code> ExceptionListener </code>
 */
public class ExceptionEvent {
    private ErrorId errorId;
    private String information;
    private Exception cause;
    private boolean handled;

    public ExceptionEvent(Exception cause, ErrorId errorId) {
        this.errorId = errorId;
        this.cause = cause;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public boolean getHandled(){
        return handled;
    }

    public Exception getCause() {
        return cause;
    }

    public ExceptionEvent(ErrorId errorId) {
        this.errorId = errorId;
    }


    public ErrorId getErrorId() {
        return errorId;
    }

    public String getInformation() {
        return information;
    }

}

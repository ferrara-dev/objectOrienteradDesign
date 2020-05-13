package observer.exceptionobserver;

import model.exception.UserException;
import exception.ErrorId;

public class ExceptionEvent {
    private ErrorId errorId;
    private String information;
    private Exception cause;
    private boolean handled;

    public ExceptionEvent(UserException userException) {
        this.errorId = userException.getErrorId();
        information = userException.getLocalizedMessage();
    }

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

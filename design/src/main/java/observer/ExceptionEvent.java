package observer;

import model.exception.UserException;
import util.exception.ErrorId;

public class ExceptionEvent {
    private ErrorId errorId;
    private String information;

    public ExceptionEvent(UserException userException) {
        this.errorId = userException.getErrorId();
        information = userException.getLocalizedMessage();
    }

    public ErrorId getErrorId() {
        return errorId;
    }

    public String getInformation() {
        return information;
    }

}

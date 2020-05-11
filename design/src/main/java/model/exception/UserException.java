package model.exception;

import util.exception.ErrorId;

/**
 * Exception of this class are shown to the use by the view
 */
public class UserException extends RuntimeException{
    String errorMessage;
    ErrorId errorId;

    public UserException (Throwable cause, ErrorId errorId){
        super(cause);
        errorMessage = cause.getMessage();
        this.errorId = errorId;
    }

    public ErrorId getErrorId() {
        return errorId;
    }

    @Override
    public String getLocalizedMessage() {
        return errorMessage;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }
}

package model.exception;


import exception.ErrorId;
import exception.notfoundexception.ApplicationException;

/**
 * Exception of this class are shown to the use by the view
 */
public class UserException extends ApplicationException {

    public UserException (Throwable cause, ErrorId errorId){
        super(cause.getMessage(), errorId);
    }

    public UserException(String s, Exception cause, ErrorId errorId){
        super(s,cause,errorId);
    }

    @Override
    public ErrorId getErrorId() {
        return errorId;
    }

}

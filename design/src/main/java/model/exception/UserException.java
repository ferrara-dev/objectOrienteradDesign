package model.exception;

/**
 * Exception of this class are shown to the use by the view
 */
public class UserException extends RuntimeException{
    String errorMessage;
    public UserException (String errorMessage, Throwable cause){
        super(errorMessage,cause);
        this.errorMessage = errorMessage;
    }

    @Override
    public String getLocalizedMessage() {
        return super.getMessage() + errorMessage;
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

package util.exception;

public class DataBaseAccessFailureException extends RuntimeException{
    ErrorId errorId;

    public DataBaseAccessFailureException(Exception cause, ErrorId errorId){
        super(cause);
        this.errorId = errorId;
    }

    public DataBaseAccessFailureException(Exception cause){
        super(cause);
    }

    public DataBaseAccessFailureException(String message, Exception cause){
        super(message,cause);
    }

    public ErrorId getErrorId() {
        return errorId;
    }
}

package exception;

/**
 * Exception that is thrown when a class from the
 * integration layer cant access the database.
 *
 * This exception is used to wrap
 * checked exceptions such as {@code SQLException}
 */
public class DataBaseAccessFailureException extends RuntimeException{
    ErrorId errorId;
    boolean fixed;

    public DataBaseAccessFailureException(Exception cause, ErrorId errorId){
        super(cause);
        this.errorId = errorId;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean getFixed(){
        return this.fixed;
    }

    public DataBaseAccessFailureException(String message, Exception cause, ErrorId errorId){
        super(message,cause);
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

package util.exception.notfoundexception;

/**
 * Runtime exception that is thrown when the cash register fails to update
 */
public class RegisterUpdateFailureException extends RuntimeException{

    public RegisterUpdateFailureException(String errorMessage, Throwable cause){
        super(errorMessage,cause);
    }

    public RegisterUpdateFailureException(Throwable cause){
        super(cause);
    }
}

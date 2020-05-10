package util.exception.notfoundexception;

/**
 * Runtime exception that is thrown when the cash register fails to update
 */
public class RegisterUpdateFailureException extends RuntimeException{

    public RegisterUpdateFailureException(Throwable cause){
        super(cause);
    }
}

package exception;

public class SystemStartUpFailureException extends RuntimeException{
    public SystemStartUpFailureException(String message, Throwable cause){
        super(message,cause);
    }
}

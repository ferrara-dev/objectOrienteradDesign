package util.exception;

public class SystemStartUpFailureException extends RuntimeException{
    public SystemStartUpFailureException(Throwable cause){
        super(cause);
    }
}

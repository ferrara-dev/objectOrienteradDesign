package exception;
/**
 * This exception is throw if the program for whatever reason
 * fails to start up.
 *
 * One example is if the register cant be started when the program starts.
 */
public class SystemStartUpFailureException extends RuntimeException{
    public SystemStartUpFailureException(String message, Throwable cause){
        super(message,cause);
    }
}

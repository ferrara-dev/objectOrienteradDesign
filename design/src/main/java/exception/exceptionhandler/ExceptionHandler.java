package exception.exceptionhandler;


import observer.exceptionobserver.ExceptionListener;
import exception.exceptionlog.ExceptionLogger;


import java.util.Objects;



/**
 * Class holding static methods used to get global access to the <code> ExceptionHandlingChain </code>
 * implementations.
 */
public class ExceptionHandler {
    private static ExceptionHandler instance;
    private static ExceptionHandlingChain exceptionHandlingChain;
    private ExceptionListener exceptionListener;
    private static ExceptionLogger exceptionLogger;

    private ExceptionHandler() {

    }

    /**
     * Singleton method used to create an instance of the class
     * and make sure that multiple instances can not be created
     * <code> synchronized </code> keyword is used to make the
     * calls to the method thread safe.
     * * @return
     */
    public static ExceptionHandler getInstance() {
        if (instance == null) {
            synchronized (ExceptionHandler.class) {
                if (instance == null) {
                    instance = new ExceptionHandler();
                }
            }
        }
        return instance;
    }

    public static void createExceptionHandlingChain(ExceptionListener exceptionListener) {
        if (exceptionHandlingChain == null) {
            exceptionHandlingChain = new NotFoundExceptionHandler(new DataBaseFailureExceptionHandler(new SystemStartUpFailureHandler(new BusinessExceptionHandler(null))));
            exceptionHandlingChain.setExceptionListener(exceptionListener);
        }
    }

    public static ExceptionHandlingChain getExceptionHandlingChain() {
        return exceptionHandlingChain;
    }

    public static void handle(Exception exception) {
        if (Objects.nonNull(exceptionHandlingChain))
            if (Objects.nonNull(exception))
                exceptionHandlingChain.handle(exception);
    }
}

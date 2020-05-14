package observer.exceptionobserver;

/**
 * Interface that is implemented by classes responsible for
 * showing error messages to the user when a exception has
 * been thrown.
 */
public interface ExceptionListener {
    void exceptionThrown(ExceptionEvent exception);
}

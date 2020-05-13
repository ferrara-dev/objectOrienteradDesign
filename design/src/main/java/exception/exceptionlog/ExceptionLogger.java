package exception.exceptionlog;

public interface ExceptionLogger {
    void logException(Exception exception);
    void logException(String additionalMessage, Exception exception);
}

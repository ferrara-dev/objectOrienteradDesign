package service.handlerpattern.exceptionhandler;

import service.handlerpattern.Handler;
import service.handlerpattern.exceptionlog.ExceptionLogger;
import view.ExceptionListener;

import java.util.Objects;

/**
 * Handles thrown exceptions by logging them using the <code> ExceptionLogger </code> interface.
 * If the user needs to be notified about the exception, the exception is wrapped and sent to
 * an implementation of <code> ExceptionListener </code> that is responsible for notifying the
 * user that a problem has occurred.
 */
public abstract class ExceptionHandlingChain implements Handler<Exception> {
    protected ExceptionListener exceptionListener;
    protected ExceptionHandlingChain successor;
    protected ExceptionLogger exceptionLogger;

    ExceptionHandlingChain(ExceptionHandlingChain successor) {
        super();
        this.successor = successor;
    }

    void setExceptionListener(ExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
        if (Objects.nonNull(successor))
            successor.setExceptionListener(exceptionListener);
    }

    public abstract void handle(Exception exception);

}

package service.handlerpattern.exceptionhandler;

import service.handlerpattern.Handler;
import service.handlerpattern.exceptionlog.ExceptionLogger;

/**
 * Handles thrown exceptions by logging them using the <code> ExceptionLogger </code> interface.
 * If the user needs to be notified about the exception, the exception is wrapped and rethrown
 * to the calling <code> ExceptionService </code> class that notifies the view.
 */
public abstract class ExceptionHandler implements Handler<Exception> {
    protected ExceptionHandler successor;
    protected ExceptionLogger exceptionLogger;

    public ExceptionHandler(ExceptionHandler successor){
        super();
        this.successor = successor;
    }

    public abstract void handle(Exception exception);

}

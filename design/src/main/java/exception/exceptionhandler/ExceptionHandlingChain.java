package exception.exceptionhandler;

import model.sale.Date;
import observer.exceptionobserver.ExceptionEvent;
import service.Handler;
import exception.exceptionlog.ExceptionLogger;
import observer.exceptionobserver.ExceptionListener;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

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

    protected void notifyListener(ExceptionEvent exceptionEvent) {
        if (Objects.nonNull(exceptionListener))
            exceptionListener.exceptionThrown(exceptionEvent);
    }

    protected void exitProgram(){

    }

    void setExceptionListener(ExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
        if (Objects.nonNull(successor))
            successor.setExceptionListener(exceptionListener);
    }

    public abstract void handle(Exception exception);

    protected void exit(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }

            }
        }).start();
    }
}

package exception.exceptionhandler;

import exception.ErrorId;
import exception.exceptionlog.ExceptionLogStrategy;
import exception.SystemStartUpFailureException;
import observer.exceptionobserver.ExceptionEvent;
import java.util.Objects;

/**
 * Implementation of <code> ExceptionHandlingChain </code> that is responsible
 * for handling exceptions of <code> SystemStartUpFailureException </code> type.
 */
public class SystemStartUpFailureHandler extends ExceptionHandlingChain {

    SystemStartUpFailureHandler(ExceptionHandlingChain successor) {
        super(successor);
        exceptionLogger = ExceptionLogStrategy.SEVERE_EXCEPTION_LOG.get();
    }

    @Override
    public void handle(Exception exception) {
        if(exception instanceof SystemStartUpFailureException){
            exceptionLogger.logException(exception);
            exception.printStackTrace();
            System.exit(-1);
        }  else {
            if (Objects.nonNull(successor)) {
                successor.handle(exception);
            } else {
                exceptionLogger = ExceptionLogStrategy.SEVERE_EXCEPTION_LOG.get();
                exceptionLogger.logException(exception);
                exceptionListener.exceptionThrown(new ExceptionEvent(ErrorId.UNDEFINED_PROBLEM));
            }
        }
    }
}

package exception.exceptionhandler;


import exception.ErrorId;
import model.exception.UserException;
import observer.exceptionobserver.ExceptionEvent;
import exception.exceptionlog.ExceptionLogStrategy;
import exception.notfoundexception.NotFoundException;

import java.util.Objects;

public class NotFoundExceptionHandler extends ExceptionHandlingChain {

    NotFoundExceptionHandler(ExceptionHandlingChain exceptionHandlingChain) {
        super(exceptionHandlingChain);
        exceptionLogger = ExceptionLogStrategy.USER_EXCEPTION_LOG.get();
    }

    @Override
    public void handle(Exception exception) {
        if (exception instanceof NotFoundException) {
            exceptionLogger.logException(exception);
            UserException userException = new UserException(exception, ((NotFoundException) exception).getErrorId());
            super.notifyListener(new ExceptionEvent(userException));

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

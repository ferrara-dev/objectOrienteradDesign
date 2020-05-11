package service.handlerpattern.exceptionhandler;


import model.exception.UserException;
import observer.ExceptionEvent;
import service.handlerpattern.exceptionlog.ExceptionLogStrategy;
import util.exception.notfoundexception.NotFoundException;

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
            exceptionListener.showException(new ExceptionEvent(userException));

        } else {
            if (Objects.nonNull(successor)) {
                successor.handle(exception);
            }
        }
    }
}

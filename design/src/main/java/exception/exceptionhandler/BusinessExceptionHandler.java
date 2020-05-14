package exception.exceptionhandler;

import exception.ErrorId;
import exception.businessruleexception.UndefinedDiscountException;
import exception.exceptionlog.ExceptionLogStrategy;
import exception.businessruleexception.BusinessLogicException;
import observer.exceptionobserver.ExceptionEvent;


import java.util.Objects;

public class BusinessExceptionHandler extends ExceptionHandlingChain {

    BusinessExceptionHandler(ExceptionHandlingChain successor) {
        super(successor);
        exceptionLogger = ExceptionLogStrategy.BUSINESS_EXCEPTION_LOG.get();
    }

    @Override
    public void handle(Exception exception) {
        if (exception instanceof BusinessLogicException) {
            BusinessLogicException businessLogicException = (BusinessLogicException) exception;
            if (businessLogicException.getCause() instanceof UndefinedDiscountException) {
                exceptionLogger.logException("Attempt to handle undefined discount rule", exception);
            } else {
                exceptionLogger.logException(exception.getMessage(), exception);
            }
            exceptionListener.exceptionThrown(new ExceptionEvent(businessLogicException, businessLogicException.getErrorId()));
            return;
        } else {
            if (Objects.nonNull(successor)) {
                successor.handle(exception);
            } else {
                exceptionLogger =  ExceptionLogStrategy.SEVERE_EXCEPTION_LOG.get();
                exceptionLogger.logException(exception);
                exceptionListener.exceptionThrown(new ExceptionEvent(ErrorId.UNDEFINED_PROBLEM));
            }
        }
    }
}

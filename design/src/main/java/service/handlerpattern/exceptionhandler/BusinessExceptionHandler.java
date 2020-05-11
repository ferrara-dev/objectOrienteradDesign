package service.handlerpattern.exceptionhandler;

import model.exception.UserException;
import observer.ExceptionEvent;
import service.handlerpattern.exceptionlog.ExceptionLogStrategy;
import util.exception.DataBaseAccessFailureException;

import java.util.Objects;

/**
 * Handles a business exception that is not displayed to the user
 */
public class BusinessExceptionHandler extends ExceptionHandlingChain {
    BusinessExceptionHandler(ExceptionHandlingChain successor) {
        super(successor);
        exceptionLogger = ExceptionLogStrategy.BUSINESS_EXCEPTION_LOG.get();
    }

    @Override
    public void handle(Exception exception){
        if(exception instanceof DataBaseAccessFailureException){
            DataBaseAccessFailureException dataBaseAccessFailureException = (DataBaseAccessFailureException) exception;
            handleDataBaseAccessFailure(dataBaseAccessFailureException);
        }
        else {
            if(Objects.nonNull(successor)){
                successor.handle(exception);
            }
        }
    }

    private void handleDataBaseAccessFailure(DataBaseAccessFailureException dataBaseAccessFailureException) {
        exceptionLogger.logException(dataBaseAccessFailureException);
        exceptionListener.showException(new ExceptionEvent(new UserException(dataBaseAccessFailureException,dataBaseAccessFailureException.getErrorId())));
        dataBaseAccessFailureException.printStackTrace();
    }
}

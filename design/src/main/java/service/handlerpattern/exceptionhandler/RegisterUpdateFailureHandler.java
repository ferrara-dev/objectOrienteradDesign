package service.handlerpattern.exceptionhandler;

import model.exception.BusinessException;
import service.handlerpattern.exceptionlog.ExceptionLogStrategy;
import util.exception.notfoundexception.RegisterUpdateFailureException;

import java.util.Objects;

/**
 * Handles a business exception that is not displayed to the user
 */
public class RegisterUpdateFailureHandler extends ExceptionHandler {

    public RegisterUpdateFailureHandler(ExceptionHandler successor) {
        super(successor);
        exceptionLogger = ExceptionLogStrategy.BUSINESS_EXCEPTION_LOG.get();
    }

    @Override
    public void handle(Exception exception){
        if(exception instanceof RegisterUpdateFailureException){
            RegisterUpdateFailureException registerUpdateFailureException = (RegisterUpdateFailureException) exception;
            handleBusinessException(registerUpdateFailureException);
        }
        else {
            if(Objects.nonNull(successor)){
                successor.handle(exception);
            }
        }
    }

    private void handleBusinessException(RegisterUpdateFailureException registerUpdateFailureException) {
        exceptionLogger.logException(registerUpdateFailureException);
        String errorMessage = "This device might need maintenance " + "\n" + "Please notify IT-service";
        BusinessException businessException = new BusinessException(errorMessage, registerUpdateFailureException);
        System.err.println(businessException.getMessage());
        throw businessException;
    }
}

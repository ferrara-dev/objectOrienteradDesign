package exception;

import factory.HandlerFactory;
import factory.IntegrationFactory;
import model.exception.BusinessException;
import org.junit.Before;
import org.junit.Test;
import service.handlerpattern.Handler;
import service.handlerpattern.exceptionlog.ExceptionLogStrategy;
import service.handlerpattern.exceptionlog.ExceptionLogger;

import util.exception.notfoundexception.NotFoundException;
import util.exception.notfoundexception.RegisterUpdateFailureException;

public class BusinessExceptionTest {
    ExceptionLogger exceptionLogger;
    Handler exceptionHandler;

    @Before
    public void startUp(){
        exceptionLogger = ExceptionLogStrategy.BUSINESS_EXCEPTION_LOG.get();

    }

    @Test (expected = NotFoundException.class)
    public void testRegisterNotFound(){
           IntegrationFactory.REGISTER_BALANCE_ACCOUNT.getDataBaseHandler().collect("ABSSCSA");
    }


    @Test (expected = BusinessException.class)
    public void testRegisterUpdateFailure2(){
        try {
            IntegrationFactory.REGISTER_BALANCE_ACCOUNT.getDataBaseHandler().collect("ABSSCSA");
        } catch (NotFoundException e){
            RegisterUpdateFailureException registerUpdateFailureException = new RegisterUpdateFailureException("Failed to update register",e);
            exceptionHandler.handle(registerUpdateFailureException);
        }
    }
}
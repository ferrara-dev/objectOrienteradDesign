package exception;

import exception.exceptionhandler.ExceptionHandler;
import factory.IntegrationFactory;
import model.amount.MonetaryValue;
import model.banking.CashPayment;
import exception.businessruleexception.BusinessLogicException;
import model.physicalobjects.Register;
import org.junit.Before;
import org.junit.Test;
import exception.exceptionlog.ExceptionLogStrategy;
import exception.exceptionlog.ExceptionLogger;

import exception.notfoundexception.NotFoundException;
import view.exceptionview.ExceptionView;

import java.math.BigDecimal;

public class BusinessExceptionHandlerTest {
    ExceptionLogger exceptionLogger;

    @Before
    public void startUp(){
        exceptionLogger = ExceptionLogStrategy.BUSINESS_EXCEPTION_LOG.get();
        ExceptionHandler.createExceptionHandlingChain(new ExceptionView());
    }

    @Test (expected = NotFoundException.class)
    public void testRegisterNotFound(){
           IntegrationFactory.REGISTER_BALANCE_ACCOUNT.getDataBaseHandler().collect("ABSSCSA");
    }

    @Test(expected = BusinessLogicException.class)
    public void testRegisterOverWithdrawal(){
        Register register = new Register();
        register.setDefault();
        MonetaryValue withdrawal = new CashPayment(new BigDecimal(10000000));
        register.withdraw(withdrawal);
    }

    @Test(expected = BusinessLogicException.class)
    public void testRegisterWithdrawalNullArg(){
        Register register = new Register();
        register.setDefault();
        register.withdraw(null);
    }


    @Test
    public void testRegisterOverWithdrawal2(){
        Register register = new Register();
        register.setDefault();
        MonetaryValue withdrawal = new CashPayment(new BigDecimal(10000000));
        try {
            register.withdraw(withdrawal);
        } catch (Exception e){
                ExceptionHandler.handle(e);
        }
    }
}
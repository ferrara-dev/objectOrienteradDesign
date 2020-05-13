package exception;

import exception.exceptionhandler.ExceptionHandler;
import exception.exceptionlog.ExceptionLogStrategy;
import exception.exceptionlog.ExceptionLogger;
import org.junit.Before;
import org.junit.Test;
import view.exceptionview.ExceptionView;

public class UndefinedRuntimeTest {
    ExceptionLogger exceptionLogger;

    @Before
    public void startUp(){
        exceptionLogger = ExceptionLogStrategy.BUSINESS_EXCEPTION_LOG.get();
        ExceptionHandler.createExceptionHandlingChain(new ExceptionView());
    }

    @Test
    public void testRunTimeException() {
            try {
                throw new NullPointerException();
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }
}

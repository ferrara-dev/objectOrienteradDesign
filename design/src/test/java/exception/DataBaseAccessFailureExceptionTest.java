package exception;

import exception.exceptionhandler.ExceptionHandler;
import factory.IntegrationFactory;
import integration.DataBaseHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import exception.exceptionhandler.ExceptionHandlingChain;
import util.AppProperties;
import view.exceptionview.ExceptionView;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataBaseAccessFailureExceptionTest {
    private FileLock lock = null;


    @Before
    public void startUp() {
        ExceptionHandler.createExceptionHandlingChain(new ExceptionView());
        try {
            AppProperties.resetDataBaseURL();
            System.out.println(AppProperties.getDataBaseURL());
            FileInputStream fis = new FileInputStream("userDB.mv.db");
            FileChannel channel = fis.getChannel();
            lock = channel.lock(0, Long.MAX_VALUE, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            if (lock != null)
                lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppProperties.resetDataBaseURL();
        System.out.println(AppProperties.getDataBaseURL());
    }

    @Test
    public void testPropertiesClass() {
        String url = AppProperties.getDataBaseURL();

    }

    @Test(expected = DataBaseAccessFailureException.class)
    public void testDataBaseAccessFailure() throws IOException {
        IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("1");
    }


    @Test
    public void testDataBaseAccessFailure2() throws IOException {
        try {
            IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("1");
        } catch (DataBaseAccessFailureException e){
            ExceptionHandler.handle(e);
        }
    }
}

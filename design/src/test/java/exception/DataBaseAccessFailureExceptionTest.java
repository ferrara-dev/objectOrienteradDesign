package exception;

import factory.IntegrationFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.handlerpattern.exceptionhandler.ExceptionHandlingChain;
import service.handlerpattern.exceptionhandler.ExceptionHandlingFactory;
import util.exception.DataBaseAccessFailureException;
import view.guiutil.ExceptionView;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class DataBaseAccessFailureExceptionTest {
    private FileLock lock = null;
    private ExceptionHandlingChain exceptionHandlingChain;

    @Before
    public void startUp() {
        try {
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
    }


    @Test(expected = DataBaseAccessFailureException.class)
    public void testDataBaseAccessFailure() throws IOException {
        IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("1");
    }

    @Test
    public void testExceptionHandling() {
        ExceptionHandlingFactory.createExceptionHandlingChain(new ExceptionView());
        exceptionHandlingChain = ExceptionHandlingFactory.getExceptionHandlingChain();
        try {
            IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("1");
        } catch (DataBaseAccessFailureException e) {
           exceptionHandlingChain.handle(e);
        }
    }

    private void lockDataBaseFile() {
        try {
            FileInputStream fis = new FileInputStream("userDB.mv.db");
            FileChannel channel = fis.getChannel();
            lock = channel.lock(0, Long.MAX_VALUE, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unlockDataBaseFile() {
        try {
            if (lock != null)
                lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

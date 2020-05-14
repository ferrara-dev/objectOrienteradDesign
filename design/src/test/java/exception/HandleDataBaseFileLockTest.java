package exception;

import factory.IntegrationFactory;
import integration.DataBaseHandler;
import integration.productdb.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import exception.exceptionhandler.ExceptionHandler;
import exception.exceptionhandler.ExceptionHandlingChain;
import util.AppProperties;
import view.exceptionview.ExceptionView;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HandleDataBaseFileLockTest {
    private FileLock lock = null;
    private ExceptionHandlingChain exceptionHandlingChain;

    @Before
    public void startUp() {

    }

    @After
    public void after() {
        AppProperties.resetDataBaseURL();
        System.out.println(AppProperties.getDataBaseURL());
        unlockDataBaseFile();
    }

    @Test
    public void testDataBaseAccessFailure() throws IOException {
        System.out.println(AppProperties.getDataBaseURL());
        Product product = (Product) IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("1");
        int stock = product.getStockstatus();
        System.out.println(stock);
        product.setStockstatus(stock - 10);
        IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().register("1",product);
    }

    @Test
    public void testExceptionHandling() {
        lockDataBaseFile();
        ExceptionHandler.createExceptionHandlingChain(new ExceptionView());
        exceptionHandlingChain = ExceptionHandler.getExceptionHandlingChain();
        try {
            System.out.println(AppProperties.getDataBaseURL());
            IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().find("1");
        } catch (DataBaseAccessFailureException e) {
            exceptionHandlingChain.handle(e);
            boolean product = IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().find("1");
            assertTrue(product);
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


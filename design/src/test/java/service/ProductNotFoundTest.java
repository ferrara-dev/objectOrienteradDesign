package service;

import factory.IntegrationFactory;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import service.handlerpattern.exceptionhandler.ExceptionHandlingChain;
import service.handlerpattern.exceptionhandler.ExceptionHandlingFactory;

import util.exception.DataBaseAccessFailureException;
import util.exception.ErrorId;
import util.exception.notfoundexception.NotFoundException;
import view.guiutil.ExceptionView;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.NonWritableChannelException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class ProductNotFoundTest {
    ExceptionView exceptionView;
    ExceptionHandlingChain exceptionHandlingChain;

    @Before
    public void startUp() {
        exceptionView = new ExceptionView();
        ExceptionHandlingFactory.createExceptionHandlingChain(exceptionView);
        exceptionHandlingChain = ExceptionHandlingFactory.getExceptionHandlingChain();
    }

    /**
     * Test invalid product id exception
     */
    @Test(expected = NotFoundException.class)
    public void testInvalidProductId1() {
        IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("11111");
    }

    /**
     * Test if the error code is correct
     */
    @Test
    public void testInvalidProductId2() {
        try {
            IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("111111");
        } catch (NotFoundException e) {
            assertEquals(ErrorId.PRODUCT_ID_NOT_FOUND.code, e.getErrorCodeValue());
        }
    }

    /**
     * Test that the message is correct
     */
    @Test
    public void testInvalidProductId3() {
        try {
            IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("111111");
        } catch (NotFoundException e) {
            assertEquals("id \"111111\"", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Test that the handling chain works
     */
    @Test
    public void testInvalidProductId4() {
        try {
            IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("111111");
        } catch (NotFoundException e) {
            exceptionHandlingChain.handle(e);
            e.printStackTrace();
        }
    }

    @Test (expected = DataBaseAccessFailureException.class)
    public void testDataBaseAccessFailure() throws IOException {
        FileLock lock = null;
        try {
            FileInputStream fis = new FileInputStream("userDB.mv.db");
            FileChannel channel = fis.getChannel();
            lock = channel.lock(0, Long.MAX_VALUE, true);

            try {
                IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("1");
            } catch (DataBaseAccessFailureException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            // handle exception
        } finally {
            try {
                lock.release();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

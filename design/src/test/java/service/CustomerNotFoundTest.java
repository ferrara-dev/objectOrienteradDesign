package service;

import factory.IntegrationFactory;
import org.junit.Before;
import org.junit.Test;
import service.handlerpattern.exceptionhandler.ExceptionHandlingChain;
import service.handlerpattern.exceptionhandler.ExceptionHandlingFactory;
import util.exception.ErrorId;
import util.exception.notfoundexception.NotFoundException;
import view.guiutil.ExceptionView;

import static org.junit.Assert.assertEquals;

public class CustomerNotFoundTest {
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
        try{
            IntegrationFactory.CUSTOMER_REPO.getDataBaseHandler().collect("111111");
        } catch (NotFoundException e){
            assertEquals(ErrorId.CUSTOMER_ID_NOT_FOUND.code, e.getErrorCodeValue());
        }
    }

    /**
     * Test that the message is correct
     */
    @Test
    public void testInvalidProductId3() {
        try{
            IntegrationFactory.CUSTOMER_REPO.getDataBaseHandler().collect("111111");
        } catch (NotFoundException e){
            assertEquals("id \"111111\"", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Test that the handling chain works
     */
    @Test
    public void testInvalidProductId4() {
        try{
            IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("111111");
        } catch (NotFoundException e){
            exceptionHandlingChain.handle(e);
            e.printStackTrace();
        }
    }
}

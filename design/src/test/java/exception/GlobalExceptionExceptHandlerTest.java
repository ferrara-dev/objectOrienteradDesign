package exception;

import factory.IntegrationFactory;
import org.junit.Before;
import org.junit.Test;
import exception.notfoundexception.NotFoundException;


import static org.junit.Assert.assertEquals;

public class GlobalExceptionExceptHandlerTest {

    @Before
    public void startUp() {
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
        IntegrationFactory.CUSTOMER_REPO.getDataBaseHandler().collect("111111");
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
    }
}

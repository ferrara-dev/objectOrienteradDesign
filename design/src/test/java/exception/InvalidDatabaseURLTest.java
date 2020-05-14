package exception;

import exception.exceptionhandler.ExceptionHandler;
import factory.IntegrationFactory;
import integration.DataBaseHandler;
import integration.productdb.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.AppProperties;
import view.exceptionview.ExceptionView;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;

import static org.junit.Assert.assertEquals;

public class InvalidDatabaseURLTest {


    @Before
    public void startUp() {
        ExceptionHandler.createExceptionHandlingChain(new ExceptionView());
    }

    @After
    public void after() {
        AppProperties.resetDataBaseURL();
        System.out.println(AppProperties.getDataBaseURL());
    }

    @Test
    public void testInvalidURL(){
        AppProperties.setDataBaseURL("A");
        System.out.println(AppProperties.getDataBaseURL());
        try {
            IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("1");
        } catch (DataBaseAccessFailureException e){
            ExceptionHandler.handle(e);
        }
    }
}

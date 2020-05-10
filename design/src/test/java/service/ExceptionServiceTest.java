package service;

import model.exception.BusinessException;
import model.exception.UserException;
import observer.EventObserver;
import org.junit.Before;
import org.junit.Test;
import service.handlerpattern.exceptionhandler.ExceptionHandler;
import service.handlerpattern.exceptionhandler.ProductNotFoundHandler;
import util.datatransferobject.SaleItemDTO;
import util.exception.notfoundexception.NotFoundException;
import util.exception.notfoundexception.ProductNotFoundException;
import view.guiutil.ExceptionView;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ExceptionServiceTest {
    ExceptionView exceptionView;
    ExceptionService exceptionService;

    @Before
    public void startUp(){
        exceptionService = new ExceptionService();
        exceptionView = new ExceptionView();
    }

    /**
     * Test ProductNotFoundException as input parameter
     */
    @Test(expected = UserException.class)
    public void testExceptionHandler1() {
        exceptionService.addObserver(exceptionView);
        ProductNotFoundException productNotFoundException = new ProductNotFoundException(new NotFoundException(),new SaleItemDTO(99,1));
        ExceptionHandler exceptionHandler = new ProductNotFoundHandler(null);
    }


    @Test
    public void testExceptionHandler2(){
        ArrayList<EventObserver> eventObservers = new ArrayList<>();
        eventObservers.add(exceptionView);
        ExceptionHandler exceptionHandler = new ProductNotFoundHandler(null);
        ProductNotFoundException productNotFoundException = new ProductNotFoundException(new NotFoundException("Not Found in database"),new SaleItemDTO(99,1));
        try {
            exceptionHandler.handle(productNotFoundException);
        }catch (UserException | BusinessException e ){
            assertEquals("No product with id 99 exist", e.getMessage());
        }
    }

    @Test
    public void testExceptionHandler3() throws NotFoundException {
        ProductNotFoundException productNotFoundException = new ProductNotFoundException(new NotFoundException("Not Found in database"),new SaleItemDTO(99,1));
        exceptionService.handleException(productNotFoundException);
    }
}

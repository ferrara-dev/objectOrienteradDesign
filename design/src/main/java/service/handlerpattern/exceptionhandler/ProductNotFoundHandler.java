package service.handlerpattern.exceptionhandler;

import model.exception.UserException;
import service.handlerpattern.exceptionlog.ExceptionLogStrategy;
import util.exception.notfoundexception.ProductNotFoundException;

import java.util.Objects;

public class ProductNotFoundHandler extends ExceptionHandler {

    public ProductNotFoundHandler(ExceptionHandler exceptionHandler) {
        super(exceptionHandler);
        exceptionLogger = ExceptionLogStrategy.USER_EXCEPTION_LOG.get();
    }

    @Override
    public void handle(Exception exception){
        if (exception instanceof ProductNotFoundException) {
            ProductNotFoundException productNotFoundException = (ProductNotFoundException) exception;
            UserException userException = configureUserException(productNotFoundException);
            throw userException;
        }
        else{
            if(Objects.nonNull(successor)){
                successor.handle(exception);
            }
        }
    }

    private UserException configureUserException(ProductNotFoundException productNotFoundException) {;
        String errorMessage = "No product with id "
                + productNotFoundException.getInvalidSaleItemDTO().getItemId()
                + " exist";
        UserException userException = new UserException(errorMessage,productNotFoundException);
        exceptionLogger.logException(userException);
        return userException;
    }
}

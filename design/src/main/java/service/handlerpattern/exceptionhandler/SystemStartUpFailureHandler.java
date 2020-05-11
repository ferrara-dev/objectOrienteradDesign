package service.handlerpattern.exceptionhandler;

public class SystemStartUpFailureHandler extends ExceptionHandlingChain {

    SystemStartUpFailureHandler(ExceptionHandlingChain successor) {
        super(successor);
    }

    @Override
    public void handle(Exception exception) {

    }
}

package service.handlerpattern.exceptionhandler;

import service.handlerpattern.exceptionlog.ExceptionLogStrategy;
import util.exception.SystemStartUpFailureException;

public class SystemStartUpFailureHandler extends ExceptionHandlingChain {

    SystemStartUpFailureHandler(ExceptionHandlingChain successor) {
        super(successor);
        exceptionLogger = ExceptionLogStrategy.STARTUP_EXCEPTION_LOG.get();
    }

    @Override
    public void handle(Exception exception) {
        if(exception instanceof SystemStartUpFailureException){
            exceptionLogger.logException(exception);
            System.exit(-1);
        } else {
            if(successor != null)
                successor.handle(exception);
        }
    }
}

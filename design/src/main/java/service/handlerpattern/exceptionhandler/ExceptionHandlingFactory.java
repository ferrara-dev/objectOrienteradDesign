package service.handlerpattern.exceptionhandler;

import view.ExceptionListener;

/**
 * Class used to create and get access to a exception handling chain
 * globally.
 *
 */
public class ExceptionHandlingFactory {
    private static ExceptionHandlingChain exceptionHandlingChain;

    private ExceptionHandlingFactory() {
    }

    public static void createExceptionHandlingChain(ExceptionListener exceptionListener) {
        if (exceptionHandlingChain == null) {
            exceptionHandlingChain = new NotFoundExceptionHandler(new BusinessExceptionHandler(null));
            exceptionHandlingChain.setExceptionListener(exceptionListener);
        }
    }

    public static ExceptionHandlingChain getExceptionHandlingChain() {
        return exceptionHandlingChain;
    }
}

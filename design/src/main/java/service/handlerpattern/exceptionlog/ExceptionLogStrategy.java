package service.handlerpattern.exceptionlog;


public enum ExceptionLogStrategy {
    USER_EXCEPTION_LOG {
        @Override
        public ExceptionLogger get() {
            return UserExceptionLogger.getInstance();
        }
    },
    STARTUP_EXCEPTION_LOG{
        @Override
        public ExceptionLogger get() {
            return SystemStartUpFailureLogger.getInstance();
        }
    },
    BUSINESS_EXCEPTION_LOG{
        @Override
        public ExceptionLogger get() {
            return BusinessExceptionLogger.getInstance();
        }
    };

    public abstract ExceptionLogger get();
}

package exception.exceptionlog;


public enum ExceptionLogStrategy {
    USER_EXCEPTION_LOG {
        @Override
        public ExceptionLogger get() {
            return UserExceptionLogger.getInstance();
        }
    },
    SEVERE_EXCEPTION_LOG{
        @Override
        public ExceptionLogger get() {
            return SevereExceptionLog.getInstance();
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

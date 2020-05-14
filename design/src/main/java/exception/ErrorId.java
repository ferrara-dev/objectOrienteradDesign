package exception;

/**
 * Class containing enum constants used to identify exception causes
 * and determine in what fashion the exception should be handled.
 *
 * Codes are:
 * 0   - Undefined exception has been thrown - this should always result
 *       in program shutdown.
 * 1XX - failed database operations caused by invalid argument
 * 2XX - system failure
 * 3XX - Database connection failure
 */
public enum ErrorId {
    UNDEFINED_PROBLEM(0),
    PRODUCT_ID_NOT_FOUND(100),
    CUSTOMER_ID_NOT_FOUND(101),
    DAILY_DISCOUNT_NOT_FOUND(102),
    REGISTER_ID_NOT_FOUND(103),
    SALE_ID_NOT_FOUND(104),
    BUSINESS_LOGIC_ERROR(105),
    REGISTER_OPERATION_FAILURE(106),
    REGISTER_STARTUP_FAILURE(200),
    PRINTER_STARTUP_FAILURE(201),
    DATABASE_ACCESS_FAILURE(300),
    DATABASE_CLOSE_ON_EXIT_FAILURE(301);
    public final long code;
    ErrorId(long code) {
        this.code = code;
    }
}

package util.exception;

/**
 * Class containing enum constants used to identify exception causes
 * and determine in what fashion the exception should be handled.
 *
 * Codes are:
 * 1XX - failed database operations caused by invalid argument
 * 2XX - system failure
 * 3XX - Database connection failure
 */
public enum ErrorId {
    PRODUCT_ID_NOT_FOUND(100),
    CUSTOMER_ID_NOT_FOUND(101),
    DAILY_DISCOUNT_NOT_FOUND(102),
    REGISTER_ID_NOT_FOUND(103),
    SALE_ID_NOT_FOUND(104),
    REGISTER_STARTUP_FAILURE(200),
    PRINTER_STARTUP_FAILURE(201),
    DATABASE_ACCESS_FAILURE(300);

    public final long code;

    ErrorId(long code) {
        this.code = code;
    }
}

package exception.notfoundexception;

import exception.ErrorId;

/**
 * Runtime exception that is thrown in the integration layer when something is not found in the database
 * This is the highest abstraction of exception regarding this.
 *
 * <code> NotFoundException </code> is ideally wrapped in a appropriate exception class
 * and rethrown by the calling service class, so that it can be handled further down in the call chain.
 */

public class NotFoundException extends RuntimeException {
    ErrorId errorId;
    public NotFoundException(String s, ErrorId errorId) {
        super(s);
        this.errorId = errorId;
    }

    public NotFoundException(Exception cause, ErrorId errorId) {
        super(cause.getMessage());
        this.errorId = errorId;
    }

    public NotFoundException(String s, Exception cause) {
        super(s, cause);
    }

    public ErrorId getErrorId() {
        return errorId;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}

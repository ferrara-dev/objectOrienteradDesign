package model.exception;

import java.util.Objects;

/**
 * Exceptions of this class are not shown to the user, they are only shown to the developer and logged to txt file.
 */
public class BusinessException extends RuntimeException {
    String errorMessage;

    public BusinessException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getLocalizedMessage() {
        if (Objects.nonNull(errorMessage))
            return super.getMessage() + errorMessage;
        return super.getMessage();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }
}

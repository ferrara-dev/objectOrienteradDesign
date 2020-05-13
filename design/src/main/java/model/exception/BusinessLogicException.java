package model.exception;

import exception.ErrorId;

import java.util.Objects;

/**
 * Exceptions of this class are not shown to the user, they are only shown to the developer and logged to txt file.
 */
public class BusinessLogicException extends RuntimeException {
    String errorMessage;
    ErrorId errorId;

    public BusinessLogicException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }

    public ErrorId getErrorId() {
        return errorId;
    }

    public BusinessLogicException(Throwable cause, ErrorId errorId) {
        super(cause);
        this.errorId = errorId;
    }


    public BusinessLogicException(String errorMessage,ErrorId errorId) {
        super(errorMessage);
        this.errorId = errorId;
        this.errorMessage = errorMessage;
    }

    public BusinessLogicException(String errorMessage, Throwable cause, ErrorId errorId) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public BusinessLogicException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public BusinessLogicException(Throwable cause) {
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

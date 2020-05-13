package exception.notfoundexception;

import exception.ErrorId;

/**
 * All exceptions that have a direct relationship to the applications
 * system operations should extend this class.
 */
public abstract class ApplicationException extends RuntimeException{
    protected ErrorId errorId;

    public ApplicationException(String s, ErrorId errorId){
        super(s);
        this.errorId = errorId;
    }

    public ApplicationException(String s, Exception cause, ErrorId errorId){
        super(s,cause);
        this.errorId = errorId;
    }

    public abstract ErrorId getErrorId();
}

package util.exception.notfoundexception;


import util.exception.ErrorId;

/**
 * Runtime exception that is thrown in the integration layer when something is not found in the database
 * This is the highest abstraction of exception regarding this.
 *
 * <code> NotFoundException </code> needs to be caught and wrapped in a appropriate exception class
 *  and rethrown by the calling service class so that it can be handled further up in the call chain.
 */

public class NotFoundException extends RuntimeException {
    ErrorId errorId;

    public NotFoundException(String s, ErrorId errorId){
        super(s);
        this.errorId = errorId;
    }

    public NotFoundException(Exception cause, ErrorId errorId){
        super(cause.getMessage(),cause);
        this.errorId = errorId;
    }

    public NotFoundException(String s, Exception cause, ErrorId errorId){
        super(s,cause);
        this.errorId = errorId;
    }

    public ErrorId getErrorId() {
        return errorId;
    }

    public long getErrorCodeValue(){
        return errorId.code;
    }

    public NotFoundException(){
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}

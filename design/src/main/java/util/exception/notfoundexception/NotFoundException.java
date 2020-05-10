package util.exception.notfoundexception;
/**
 * Runtime exception that is thrown in the integration layer when something is not found in the database
 * This is the highest abstraction of exception regarding this.
 *
 * <code> NotFoundException </code> needs to be caught and wrapped in a appropriate exception class
 *  and rethrown by the calling service class so that it can be handled further up in the call chain.
 */

public class NotFoundException extends RuntimeException {
    public final String ITEM_NOT_FOUND_MESSAGE = "ITEM NOT FOUND \n" +
            " please try again";
    public final String CUSTOMER_NOT_FOUND_MESSAGE =  "CUSTOMER ID NOT FOUND \n " +
            "The id that has been entered can not be found in the database " +
            "\n please try again";
    private Object invalidDTO;

    public NotFoundException(Exception cause, String s, Object invalidDTO){
        super(s,cause);
        this.invalidDTO = invalidDTO;
    }

    public NotFoundException(){
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public NotFoundException(String s){
        super(s);
    }

}

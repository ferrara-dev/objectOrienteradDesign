package util.exception;


/**
 * runtime exception that is thrown when something is not found in the database
 */
public class NotFoundException extends RuntimeException {
    public final String ITEM_NOT_FOUND_MESSAGE = "ITEM NOT FOUND \n" +
            " please try again";
    public final String CUSTOMER_NOT_FOUND_MESSAGE =  "CUSTOMER ID NOT FOUND \n " +
            "The id that has been entered can not be found in the database " +
            "\n please try again";

    public NotFoundException(){
        super();
    }

    public NotFoundException(String s){

        super(s);
    }

}

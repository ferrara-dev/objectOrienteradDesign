package exception.businessruleexception;

/**
 * This exception is thrown if an attempt is made to process a undefined
 * discount rule in the <code> DiscountRuleIdentifier </code> or any of
 * its successors.
 */
public class UndefinedDiscountException extends RuntimeException{

    public UndefinedDiscountException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

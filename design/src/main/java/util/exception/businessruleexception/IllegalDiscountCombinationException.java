package util.exception.businessruleexception;
/**
 * Exception that is thrown when a illegal combination of discounts occurs
 * Only one discount can be applied per item, if an attempt is made to
 * apply another discount, the discount with the highest price reduction
 * will be applied.
 */
public class IllegalDiscountCombinationException extends RuntimeException  {

    public IllegalDiscountCombinationException(){
        super();
    }


    public IllegalDiscountCombinationException(String cause){
        super(cause);
    }
}

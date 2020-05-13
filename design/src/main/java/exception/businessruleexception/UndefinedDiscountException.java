package exception.businessruleexception;

public class UndefinedDiscountException extends RuntimeException{
    public UndefinedDiscountException(){
        super();
    }
    public UndefinedDiscountException(String message){
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

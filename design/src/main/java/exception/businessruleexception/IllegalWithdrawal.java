package exception.businessruleexception;

import org.h2.api.ErrorCode;

public class IllegalWithdrawal extends Exception{
    public IllegalWithdrawal(String message, ErrorCode errorCode){
        super();
    }

    public IllegalWithdrawal(String message){
        super();
    }
}

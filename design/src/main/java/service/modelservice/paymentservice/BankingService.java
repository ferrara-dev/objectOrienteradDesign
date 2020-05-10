package service.modelservice.paymentservice;
import model.transaction.BankTransaction;
import service.handlerpattern.Handler;


public class BankingService {
    private Handler bankTransactionHandler;

    public BankingService(){
    }

    public void handleBankingRequest(BankTransaction bankTransaction){
        try {
            bankTransactionHandler.handle(bankTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package model.physicalobjects;
import model.amount.Amount;
import model.amount.Payment;

public class Register {
    Store store;
    private static double balance;

    public Register(Store store){
        this.store = store;
    }

    public void enterPayment(Amount payment){
        setBalance(balance + payment.getNumber().doubleValue());
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }


    public double withdraw(double amountToWithdraw){
        if(balance >= amountToWithdraw){
            balance = balance + amountToWithdraw;
            return amountToWithdraw;
        }
        else
            throw new IllegalArgumentException();
    }
}

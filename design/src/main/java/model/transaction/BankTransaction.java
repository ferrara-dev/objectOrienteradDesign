package model.transaction;
import model.banking.MonetaryAmount;
import model.sale.Date;
import model.sale.saleinformation.salestate.State;
import service.visitor.Transaction;

import java.math.BigDecimal;

public class BankTransaction implements Transaction<BigDecimal> {
    private String accountName;
    private MonetaryAmount amount;

    public BankTransaction(String accountName){
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public Class <?> getTransactionClass(){
        return amount.getClass();
    }

    public void setAsDeposit(BigDecimal bigDecimal){
        amount = new Deposit(bigDecimal);
    }

    public void setAsDeposit(){
        amount = new Deposit();
    }

    public void setAsWithdrawal(BigDecimal bigDecimal){
        amount = new Withdrawal(bigDecimal);
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.amount.setNumber(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return this.amount.getNumber();
    }

    @Override
    public String getType() {
        return amount.getType();
    }

    @Override
    public Date getDate() {
        return null;
    }


    @Override
    public void setState(State state) {

    }

    @Override
    public void notifyListener() {

    }

    class Withdrawal extends MonetaryAmount {
        public Withdrawal(BigDecimal bigDecimal){
            super.initDefault();
            super.setNumber(bigDecimal);
        }
    }

    class Deposit extends MonetaryAmount{
        public Deposit(BigDecimal bigDecimal){
            super.initDefault();
            super.setNumber(bigDecimal);
        }

        public Deposit(){
            super.initDefault();
        }

        @Override
        public String getType(){
            return "Deposit";
        }
    }
}

package model.banking;
import factory.builderpattern.BankAccountBuilder;
import service.visitor.Transaction;
import model.sale.Date;
import java.math.BigDecimal;
import java.util.HashSet;

public class BankAccount {
    private String id;
    private BigDecimal balance;
    private Date dateCreated;
    private HashSet<Transaction> accountTransactions;

    public void initDefault(BankAccountBuilder bankAccountBuilder){
        id = bankAccountBuilder.getId();
        dateCreated = bankAccountBuilder.getDateCreated();
        accountTransactions = bankAccountBuilder.getAccountTransactions();
        balance = bankAccountBuilder.getBalance();
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setAccountTransactions(HashSet<Transaction> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashSet<Transaction> getAccountTransactions() {
        return accountTransactions;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getId() {
        return id;
    }
}

package factory.builderpattern;

import factory.Builder;
import model.banking.BankAccount;
import service.visitor.Transaction;
import model.sale.Date;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.UUID;

public class BankAccountBuilder implements Builder <BankAccount> {
    private String id;
    private BigDecimal balance;
    private Date dateCreated;
    private HashSet<Transaction> accountTransactions;

    public BankAccountBuilder setBalance() {
        balance = new BigDecimal(0);
        return this;
    }

    public BankAccountBuilder setDateCreated() {
        this.dateCreated = util.Calendar.getDate();
        return this;
    }

    public BankAccountBuilder setAccountTransactions() {
        this.accountTransactions = new HashSet<>();
        return this;
    }

    public BankAccountBuilder setId() {
        this.id = UUID.randomUUID().toString();
        return this;
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

    @Override
    public BankAccount build() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.initDefault(this);
        return bankAccount;
    }
}

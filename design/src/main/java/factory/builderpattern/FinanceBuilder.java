package factory.builderpattern;

import factory.Factory;
import model.banking.BankAccount;
import model.banking.CashPayment;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;


public enum FinanceBuilder {
    BANK_ACCOUNT{
        @Override
        public Factory<BankAccount, BigDecimal> get() {
            return new Factory<BankAccount, BigDecimal>() {
                @Override
                public BankAccount create(BigDecimal initialBalance) {
                    return new BankAccountBuilder()
                            .setAccountTransactions()
                            .setBalance()
                            .setDateCreated()
                            .setDateCreated()
                            .build();
                }
            };
        }
    },
    MONEY{
        @Override
        public Factory<MonetaryAmount,Long> get() {
            return new Factory <MonetaryAmount,Long>(){
                private CurrencyUnit se = Monetary.getCurrency("SEK");
                @Override
                public MonetaryAmount create(Long amount) {
                    MonetaryAmount money = Monetary.getDefaultAmountFactory()
                            .setCurrency(se).setNumber(amount).create();
                    return money;
                }
            };
        }
    };

    public abstract Factory get();
}

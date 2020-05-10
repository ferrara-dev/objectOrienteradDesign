package Integration;

import factory.builderpattern.FinanceBuilder;
import integration.Bank;
import integration.DataBaseHandler;
import factory.IntegrationFactory;
import integration.saledb.AccountingHandler;

import model.banking.Balance;
import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import model.banking.BankAccount;
import util.exception.notfoundexception.NotFoundException;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BankTest {
    Bank bank;
    BankAccount bankAccount;

    @Before
    public void startUp() {
        bank = AccountingHandler.getInstance();
    }

    @Test
    public void testBank() throws NotFoundException {
        String account = "RegisterOne";
        DataBaseHandler dataBaseHandler = IntegrationFactory.REGISTER_BALANCE_ACCOUNT.getDataBaseHandler();

        Balance balance = (Balance) dataBaseHandler.collect(account);
        System.out.println(balance.getNumber().toString());

        balance.increaseValue(new BigDecimal(100));

        dataBaseHandler.register(account,balance);

        balance = (Balance) dataBaseHandler.collect(account);
        System.out.println(balance.getNumber().toString());
    }

    @Test
    public void givenAmounts_whenStringified_thanEquals() {

        CurrencyUnit usd = Monetary.getCurrency("USD");
        MonetaryAmount fstAmtUSD = Monetary.getDefaultAmountFactory()
                .setCurrency(usd).setNumber(200).create();
        Money moneyof = Money.of(12, usd);
        FastMoney fastmoneyof = FastMoney.of(2, usd);
        CurrencyUnit se = Monetary.getCurrency("SEK");
        System.out.println(se.toString());
        Money monetaryAmount = (Money) FinanceBuilder.MONEY.get().create(0L);
        System.out.println(monetaryAmount.toString());
        assertEquals("USD", usd.toString());
        assertEquals("USD 12", moneyof.toString());
        assertEquals("USD 2.00000", fastmoneyof.toString());
    }
}

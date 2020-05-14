package model;

import model.amount.MonetaryValue;
import model.amount.Balance;
import model.banking.CashPayment;
import model.physicalobjects.Register;
import org.junit.Test;

import static junit.framework.TestCase.*;

import java.math.BigDecimal;

public class TestRegister {

    @Test
    public void testRegisterWithdraw() {
        Register register = new Register();
        register.setDefault();
        Balance registerBalance = new Balance();
        registerBalance.setNumber(new BigDecimal(1000));
        register.setBalance(registerBalance);
        MonetaryValue monetaryValue = new Balance();
        monetaryValue.setNumber(registerBalance.getNumber());
        Balance withdrawnBalance = (Balance) register.withdraw(monetaryValue);
        assertEquals(withdrawnBalance.getNumber().doubleValue(), 1000, 0);
    }

    @Test
    public void testRegisterDeposit() {
        Register register = new Register();
        register.setDefault();
        Balance balance = new Balance();
        balance.setDefault();

        CashPayment cashPayment = new CashPayment();
        cashPayment.setDefault();
        cashPayment.setNumber(new BigDecimal(1000));
        register.enterPayment(cashPayment);
        assertEquals(1000,register.getBalance().getNumber().doubleValue(),0);
    }
}

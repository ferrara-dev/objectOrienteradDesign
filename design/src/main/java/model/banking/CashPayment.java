package model.banking;


import model.amount.Currency;
import model.amount.MonetaryValue;

import java.math.BigDecimal;

/**
 * Amount class representing a payment
 */
public final class CashPayment implements Payment{
    private BigDecimal number = new BigDecimal("0");
    private Currency currency = Currency.SEK;
    private final String paymentMethod = CASH;

    public CashPayment(BigDecimal number){
        setDefault();
        this.number = number;
    }

    public CashPayment(){

    }

    @Override
    public void setDefault() {
        this.number = new BigDecimal("0");
        this.currency = Currency.SEK;
    }

    public String getType() {
        return paymentMethod;
    }

    @Override
    public BigDecimal getNumber() {
        return number;
    }

    @Override
    public void setNumber(BigDecimal monetaryAmount) {
        this.number = monetaryAmount;
    }

    @Override
    public void decreaseValue(BigDecimal monetaryAmount) {
        number = number.subtract(monetaryAmount);
    }

    @Override
    public void increaseValue(BigDecimal monetaryAmount) {
        number = number.add(monetaryAmount);
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public void multiply(BigDecimal monetaryAmount) {
        number = number.multiply(monetaryAmount);
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean compare(MonetaryValue monetaryValue) {
        if (this.currency == monetaryValue.getCurrency())
            return true;
        return false;
    }

    @Override
    public boolean isLessThan(MonetaryValue monetaryValue) {
        if (this.currency == monetaryValue.getCurrency()) {
            if (number.compareTo(monetaryValue.getNumber()) == -1)
                return true;
        }
        return false;
    }

    @Override
    public boolean largerOrEquals(MonetaryValue monetaryValue) {
        if (this.currency == monetaryValue.getCurrency()) {
            if (number.compareTo(monetaryValue.getNumber()) == 1)
                return true;
            if (number.compareTo(monetaryValue.getNumber()) == 0)
                return true;
        }
        return false;
    }

    public boolean zeroOrLess(){
        if(number.doubleValue() <= 0)
            return true;
        else
            return false;
    }
}

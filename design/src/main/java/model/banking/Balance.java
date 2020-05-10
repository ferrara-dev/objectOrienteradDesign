package model.banking;

import model.amount.Currency;
import model.amount.MonetaryValue;
import util.datatransferobject.BalanceDTO;

import java.math.BigDecimal;

public final class Balance implements MonetaryValue {
    private BigDecimal number;
    private Currency currency = Currency.SEK;
    public Balance(){

    }

    public Balance(BalanceDTO balanceDTO){
        number = new BigDecimal(balanceDTO.getAmount());

    }
    public void setDefault(){
        this.number = new BigDecimal("0");
        this.currency = Currency.SEK;
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
        number.multiply(monetaryAmount);
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


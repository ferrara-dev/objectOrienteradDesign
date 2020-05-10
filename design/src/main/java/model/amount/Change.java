package model.amount;

import java.math.BigDecimal;

public class Change implements MonetaryValue{
    private BigDecimal number = new BigDecimal("0");
    private Currency currency  = Currency.SEK;

    @Override
    public void setDefault() {
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
}

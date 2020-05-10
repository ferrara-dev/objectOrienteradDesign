package model.amount;

import java.math.BigDecimal;

public interface MonetaryValue {

    void setDefault();

    BigDecimal getNumber();

    void setNumber(BigDecimal monetaryAmount);

    void decreaseValue(BigDecimal monetaryAmount);

    void increaseValue(BigDecimal monetaryAmount);

    void setCurrency(Currency currency);

    void multiply(BigDecimal monetaryAmount);

    Currency getCurrency();

    boolean compare(MonetaryValue monetaryValue);

    boolean isLessThan(MonetaryValue monetaryValue);

    boolean largerOrEquals(MonetaryValue monetaryValue);
}
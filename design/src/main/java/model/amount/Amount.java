package model.amount;

import java.math.BigDecimal;

/**
 * Interface that is to be implemented by classes representing an amount
 */
public interface Amount {
    String MONETARY = "Monetary amount";
    String getType();
    BigDecimal getNumber();
    void subtract(Amount amount);
    void setNumber(BigDecimal number);
    boolean equals(Amount amount);
}

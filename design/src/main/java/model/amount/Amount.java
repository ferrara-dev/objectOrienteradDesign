package model.amount;

public interface Amount {
    String MONETARY = "Monetary amount";
    boolean compare(Amount amount);
    String getType();
    Number getNumber();
    Number subtract(Amount amount);
    void setNumber(Number number);
}

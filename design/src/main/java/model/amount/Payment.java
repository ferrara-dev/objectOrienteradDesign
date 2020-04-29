package model.amount;

/**
 * Class representing a payment of items
 */
public class Payment implements Amount {
    private double number;
    private final String type;

    public Payment(Double number) {
        this.number = number;
        type = MONETARY;
    }

    public void setAmountPayed(double amountPayed) {
        this.number = amountPayed;
    }

    public double getAmountPayed() {
        return number;
    }

    @Override
    public boolean compare(Amount amount) {
        if (amount == null)
            return false;

        if (amount.getType().equals(this.type))
            return true;

        return false;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public Double getNumber() {
        return this.number;
    }

    @Override
    public Number subtract(Amount amount) {
        return null;
    }

    @Override
    public void setNumber(Number number) {
        this.number = number.doubleValue();
    }
}

package model.amount;

/**
 * Amount class representing a payment
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

    /**
     * Overriden to compare if two amounts are
     * of the same type
     * @param amount
     * @return
     */
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

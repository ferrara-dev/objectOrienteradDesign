package model.banking;

import model.amount.Amount;
import java.math.BigDecimal;

public class MonetaryAmount implements Amount {
    private static final String type = MONETARY;
    private BigDecimal number;

    public void initDefault(){
        this.number = new BigDecimal(0);
    }

    public void addTo(MonetaryAmount amount) {
        number = number.add(amount.getNumber());
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public BigDecimal getNumber() {
        return number;
    }

    @Override
    public void subtract(Amount amount) {
        if(amount.getClass().equals(this.getClass())){
            number.subtract(((MonetaryAmount) amount).getNumber());
        }
    }

    @Override
    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    @Override
    public boolean equals(Amount amount){
        if(amount.getClass().equals(this.getClass())){
            if(amount.getNumber().equals(this.number)){
                return true;
            }
        }
        return false;
    }
}

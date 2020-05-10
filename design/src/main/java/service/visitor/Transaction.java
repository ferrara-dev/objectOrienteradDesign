package service.visitor;

import model.sale.Date;
import model.sale.saleinformation.salestate.State;

import java.math.BigDecimal;

public interface Transaction <T>{
    void setBalance( T t );
    BigDecimal getBalance();
    String getType();
    Date getDate();
    String toString();
    void setState(State state);
    void notifyListener();
}

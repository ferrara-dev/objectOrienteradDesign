package model.physicalobjects;

import model.ObservableModel;
import model.amount.MonetaryValue;
import model.banking.Balance;
import model.banking.Payment;
import model.exception.BusinessLogicException;
import observer.modelobserver.EventObserver;
import observer.modelobserver.ObservedEvent;
import observer.modelobserver.PropertyChangeEvent;
import exception.ErrorId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;


public class Register implements ObservableModel {
    private ArrayList<EventObserver> observers;
    private static Balance balance;

    public Register() {

    }

    public void setDefault() {
        observers = new ArrayList<>();
        balance = new Balance();
        balance.setDefault();
    }

    public Register(BigDecimal bigDecimal) {
        observers = new ArrayList<>();
        this.balance = new Balance();
        balance.setDefault();
        balance.setNumber(bigDecimal);
    }

    public void enterPayment(Payment payment) {
        if(Objects.isNull(payment))
            throw new BusinessLogicException("Payment value is null ",ErrorId.BUSINESS_LOGIC_ERROR);
        notifyObservers(new PropertyChangeEvent("balance", balance, this.balance));
        balance.increaseValue(payment.getNumber().abs());
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public Balance getBalance() {
        return balance;
    }

    /**
     * Withdraw amount of <code> MonetaryValue </code> from the register.
     * If the amount is larger then the current balance a <code> BusinessException </code>
     * is thrown.
     *
     * @param amount
     * @return
     */
    public MonetaryValue withdraw(MonetaryValue amount) throws BusinessLogicException {
        if (Objects.isNull(amount))
            throw new BusinessLogicException("Withdrawn value is null ", ErrorId.BUSINESS_LOGIC_ERROR);

        if (balance.getNumber().doubleValue() < amount.getNumber().doubleValue())
            throw new BusinessLogicException("Withdrawal exceeds register balance ", ErrorId.BUSINESS_LOGIC_ERROR);

        BigDecimal amountToWithDraw = new BigDecimal(amount.getNumber().doubleValue());
        balance.decreaseValue(amountToWithDraw.abs());
        notifyObservers(new PropertyChangeEvent("balance", balance, this.balance));
        return amount;
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {
        if (Objects.nonNull(observers))
            for (EventObserver eventObserver : observers) {
                eventObserver.newEvent(observedEvent);
            }
    }

    @Override
    public void addObserver(EventObserver eventObserver) {
        observers.add(eventObserver);
    }

    @Override
    public void removeObserver(EventObserver eventObserver) {

    }

    public void setObservers(ArrayList<EventObserver> observers) {
        this.observers = observers;
        notifyObservers(new PropertyChangeEvent("balance", balance, balance));
    }


}

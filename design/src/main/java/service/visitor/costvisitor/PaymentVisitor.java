package service.visitor.costvisitor;

import factory.IntegrationFactory;
import model.amount.Change;
import model.amount.RunningTotal;
import model.banking.Payment;
import model.physicalobjects.Register;
import model.sale.saleinformation.cost.CostDetail;
import integration.PhysicalObjectsRepository;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
import service.visitor.Visitor;
import util.datatransferobject.CostDTO;
import util.exception.notfoundexception.NotFoundException;
import util.exception.notfoundexception.RegisterUpdateFailureException;

/**
 * Implementation of the <code> Visitor </code> interface.
 *
 * This visitor implementation is used to update register balance and
 * <code> CostDetail </code> of the <code> SaleTransaction </code>.
 */

public class PaymentVisitor implements Visitor<CostDetail,Payment> {
    private Payment payment;
    private static PaymentVisitor instance;

    private PaymentVisitor() {
    }

    /**
     * Singleton method used to create an instance of the class
     * and make sure that multiple instances can not be created
     * <code> synchronized </code> keyword is used to make the
     * calls to the method thread safe.
     * * @return
     */
    public static Visitor<CostDetail, Payment>  getInstance() {
        if (instance == null) {
            synchronized (PaymentVisitor.class) {
                if (instance == null) {
                    instance = new PaymentVisitor();
                }
            }
        }
        return instance;
    }

    @Override
    public void setData(Payment payment) {
        this.payment = payment;
    }

    /**
     * Override to perform operations on <code> CostDetail </code> of a
     * <code> SaleTransaction </code>
     * @param costDetail
     * @throws NotFoundException is thrown from the database if updating
     *                           the database with the new register balance
     *                           fails because of an invalid id.
     */
    @Override
    public void processElement(CostDetail costDetail) {
        Register register = PhysicalObjectsRepository.getInstance().getCashRegister();
        register.enterPayment(payment);
        RunningTotal runningTotal = costDetail.getRunningTotal();
        runningTotal.decreaseValue(payment.getNumber());
        if(runningTotal.zeroOrLess()){
            Change change = new Change();
            change.setNumber(runningTotal.getNumber().abs());
            costDetail.setChange(change);
            register.withdraw(change);
            runningTotal.setDefault();
        }
        try {
            IntegrationFactory.REGISTER_BALANCE_ACCOUNT.getDataBaseHandler().register("RegisterOne", register);
        } catch (NotFoundException e){
            throw new RegisterUpdateFailureException(e);
        }
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {

    }

    @Override
    public void addObserver(EventObserver eventObserver) {

    }

    @Override
    public void removeObserver(EventObserver eventObserver) {

    }
}

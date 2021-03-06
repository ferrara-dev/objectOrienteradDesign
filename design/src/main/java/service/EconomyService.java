
package service;

import model.banking.CashPayment;
import model.sale.saleinformation.SaleTransaction;
import service.visitor.Visitor;
import factory.VisitorFactory;
import util.datatransferobject.PaymentDTO;

import java.math.BigDecimal;

/**
 * Service class responsible for processing payments and to
 * update register balance.
 *
 * The operations are performed using implementations of the <code> Visitor </code>
 * interface.
 */
public class EconomyService {
    private Visitor visitor;

    public EconomyService() {

    }

    /**
     * Pay the sale transaction.
     * @param paymentDTO
     * @param saleTransaction
     */
    public void paySaleTransaction(PaymentDTO paymentDTO, SaleTransaction saleTransaction) {
        preparePaymentVisitor(paymentDTO);
        saleTransaction.getCost().acceptVisitor(visitor);
        if (saleTransaction.getCost().getRunningTotal().zeroOrLess()) {
            prepareSaleStateVisitor(saleTransaction);
            saleTransaction.getSaleState().acceptVisitor(visitor);
        }
    }

    private void preparePaymentVisitor(PaymentDTO paymentDTO) {
        if (paymentDTO.getPaymentMethod().equals("Cash Payment")) {
            CashPayment cashPayment = new CashPayment();
            cashPayment.setNumber(new BigDecimal(paymentDTO.getAmount()));
            visitor = VisitorFactory.PAYMENT_VISITOR.getVisitor();
            visitor.setData(cashPayment);
        }
    }

    private void prepareSaleStateVisitor(SaleTransaction saleTransaction){
        visitor = VisitorFactory.SALE_STATE_VISITOR.getVisitor();
        visitor.setData(saleTransaction);
    }

}

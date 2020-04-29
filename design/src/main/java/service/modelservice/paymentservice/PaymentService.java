package service.modelservice.paymentservice;

import model.amount.Amount;
import model.amount.Payment;
import model.physicalobjects.Register;
import service.modelservice.Service;
import service.modelservice.saleservice.SaleService;
import startup.layer.ServiceCreator;

public class PaymentService implements Service  {
    SaleService saleService;
    Register register;

    /**
     * Creates an instance of the <code> PaymentService </code>
     * takes the <code> serviceCreator </code> as parameter to
     * get access to the saleService and register.
     * @param serviceCreator
     */
    public PaymentService(ServiceCreator serviceCreator){
        this.saleService = serviceCreator.getSaleService();
        register = serviceCreator.getPhysicalObjectsRepository().getCashRegister();
    }

    /**
     * Process payment made by the customer.
     * Creates a new instance of <code> Amount </code>,
     * goes on to calculate and compare the payment to
     * the totalCost of the sale and perform logic
     * to update the register and number in <code> Cost </code>
     * @param amount
     */
    public void processPayment(double amount){
        Amount payment = new Payment(amount);
        register.enterPayment(payment);
        calculatePayment(payment);
    }

    private void calculatePayment(Amount payment){
        Amount costToPay = saleService.getSaleCost();
        double result = costToPay.getNumber().doubleValue() - payment.getNumber().doubleValue();
        if(result <= 0){
            double cashBack = register.withdraw(result);
            costToPay.setNumber(0);
            saleService.finalizeSale(cashBack);
        }
        else
            costToPay.setNumber(result);
    }

    @Override
    public PaymentService getInstance() {
        return null;
    }


}

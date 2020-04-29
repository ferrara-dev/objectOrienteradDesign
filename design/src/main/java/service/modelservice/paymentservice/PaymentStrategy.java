package service.modelservice.paymentservice;

import model.amount.Payment;

public interface PaymentStrategy{

    Payment process(Object o);
}

package util.datatransferobject;

public class PaymentDTO implements DataTransferObject{
    private double amount;
    private String paymentMethod;

    public PaymentDTO(double amount, String paymentMethod){
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}

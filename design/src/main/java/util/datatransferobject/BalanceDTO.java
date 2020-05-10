package util.datatransferobject;

import model.amount.Currency;
import model.banking.Balance;

public class BalanceDTO implements DataTransferObject{
    private String amount;
    private String currency;

    public void setAttributes(Balance balance){
        amount = String.valueOf(balance.getNumber().toString());
        currency = balance.getCurrency().toString();
    }

    public void setDefault(){
        currency = Currency.SEK.toString();
        amount = "0";
    }
    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

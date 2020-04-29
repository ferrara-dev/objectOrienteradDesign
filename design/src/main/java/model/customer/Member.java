package model.customer;

import util.datatransferobject.CustomerDTO;
import model.sale.Date;
import model.sale.Sale;
import java.util.ArrayList;

public class Member implements Customer{
    private String name;
    private CustomerId customerId;
    private Sale saleInProgress;
    private ArrayList<Sale> registeredPurchases = new ArrayList<>();

    public Member( ){

    }

    public Sale getSaleInProgress() {
        return saleInProgress;
    }

    public void setAttributes(CustomerDTO customerDTO){
        this.customerId = new CustomerId();
        customerId.setPersonalNumber(customerDTO.getCustomerId());
        this.name = customerDTO.getName();
    }

    public String getName() {
        return name;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public ArrayList<Sale> getRegisteredPurchases() {
        return registeredPurchases;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setRegisteredPurchases(ArrayList<Sale> registeredPurchases) {
        this.registeredPurchases = registeredPurchases;
    }

    public void addSaleToRegisteredPurchases(Sale sale){
        registeredPurchases.add(sale);
    }

    public void setSaleInProgress(Sale saleInProgress){
        this.saleInProgress  = saleInProgress;
    }

    public void getSaleByDate(Date date){
        for(Sale sale: registeredPurchases){

        }
    }
}

package model.customer;

import model.sale.saleinformation.SaleTransaction;
import util.datatransferobject.CustomerDTO;

import java.util.ArrayList;

/**
 * Class representing a store member
 * The class has an empty constructor because
 * it is stored as an json object in database.
 * The fields are instead initiated with the
 * <code> setAttributes(CustomerDTO customerDTO) </code>
 * method.
 */
public class Member implements Customer{
    private String name;
    private CustomerId customerId;
    private SaleTransaction saleInProgress;
    private ArrayList<SaleTransaction> registeredPurchases = new ArrayList<>();

    public Member( ){

    }
    /**
     * Add a finished sale to the member
     * @param sale
     */
    public void addSaleToRegisteredPurchases(SaleTransaction sale){
        registeredPurchases.add(sale);
    }

    /**
     * Set the sale that the member is currently performing
     * @param saleInProgress
     */
    public void setSaleInProgress(SaleTransaction saleInProgress){
        this.saleInProgress  = saleInProgress;
    }

    public SaleTransaction getSaleInProgress() {
        return saleInProgress;
    }

    /**
     * Set the attribute field
     * @param customerDTO
     */
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

    public ArrayList<SaleTransaction> getRegisteredPurchases() {
        return registeredPurchases;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setRegisteredPurchases(ArrayList<SaleTransaction> registeredPurchases) {
        this.registeredPurchases = registeredPurchases;
    }

}

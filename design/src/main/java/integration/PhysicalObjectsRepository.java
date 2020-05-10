package integration;


import factory.IntegrationFactory;
import model.banking.Balance;
import model.transaction.saleTransaction.SaleTransaction;
import observer.EventObserver;
import util.datatransferobject.ReceiptDTO;
import model.physicalobjects.Address;
import model.physicalobjects.ContactInformation;
import model.physicalobjects.Register;
import model.physicalobjects.Store;
import util.exception.SystemStartUpFailureException;
import util.exception.notfoundexception.NotFoundException;

import java.util.ArrayList;

/**
 * class used to initialize all physical objects at program startup,
 * holds references and performs operations on physicalObjects
 */
public class PhysicalObjectsRepository {
    private Register cashRegister;
    private final Store store;
    private static PhysicalObjectsRepository instance;


    /**
     *  Singleton method used to create an instance of the class
     *  and make sure that multiple instances can not be created
     *  <code> synchronized </code> keyword is used to make the
     *  calls to the method thread safe.
     * * @return
     */
    public static PhysicalObjectsRepository getInstance() {
        if(instance == null){
            synchronized (PhysicalObjectsRepository.class) {
                if(instance == null){
                    instance = new PhysicalObjectsRepository();
                }
            }
        }
        return instance;
    }

    public void setCashRegister(Register cashRegister) {
        this.cashRegister = cashRegister;
    }

    public void startUpRegister(ArrayList<EventObserver> eventObservers) {
        if(cashRegister == null){
            try {
                cashRegister = (Register) IntegrationFactory.REGISTER_BALANCE_ACCOUNT.getDataBaseHandler().collect("RegisterOne");
            }
            catch (NotFoundException e){
                throw new SystemStartUpFailureException(e);
            }
            cashRegister.setObservers(eventObservers);
        }
    }

    public void startUpPrinter(ArrayList<EventObserver> eventObservers){

    }

    private PhysicalObjectsRepository(){
        Address address = new Address("Store street 123", "Store City","Sweden","191 49");
        ContactInformation contactInformation = new ContactInformation("spof@kth.se", "070 123 4567");
        store = new Store(address,contactInformation);
    }

    /**
     * Prints the receipt
     *
     * @param sale
     */
    public void printReceipt(SaleTransaction sale){
       Printer.getInstance().print(new ReceiptDTO(store,sale));
    }

    public Register getCashRegister() {
        return cashRegister;
    }

    public Store getStore() {
        return store;
    }
}

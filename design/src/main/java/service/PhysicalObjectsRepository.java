package service;


import util.datatransferobject.ReceiptDTO;
import integration.Printer;
import model.physicalobjects.Address;
import model.physicalobjects.ContactInformation;
import model.physicalobjects.Register;
import model.physicalobjects.Store;
import model.sale.Sale;

/**
 * class used to initialize all physical objects at program startup,
 * holds references and performs operations on physicalObjects
 */
public class PhysicalObjectsRepository {
    private final Register cashRegister;
    private final Store store;
    private final Address address;
    private final ContactInformation contactInformation;

    public PhysicalObjectsRepository(){
        address = new Address("Store street 123", "Store City","Sweden","191 49");
        contactInformation = new ContactInformation("spof@kth.se", "070 123 4567");
        store = new Store(address,contactInformation);
        cashRegister = new Register(store);

    }

    /**
     * Prints the receipt
     *
     * @param sale
     */
    public void printReceipt(Sale sale){
       Printer.getInstance().print(new ReceiptDTO(store,sale));
    }

    public Register getCashRegister() {
        return cashRegister;
    }

    public Store getStore() {
        return store;
    }
}

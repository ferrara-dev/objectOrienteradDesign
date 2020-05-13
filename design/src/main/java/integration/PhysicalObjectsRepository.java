package integration;


import factory.IntegrationFactory;
import model.sale.saleinformation.SaleTransaction;
import observer.modelobserver.EventObserver;
import util.datatransferobject.ReceiptDTO;
import model.physicalobjects.Address;
import model.physicalobjects.ContactInformation;
import model.physicalobjects.Register;
import model.physicalobjects.Store;
import exception.DataBaseAccessFailureException;
import exception.SystemStartUpFailureException;
import exception.notfoundexception.NotFoundException;

import java.util.ArrayList;

/**
 * class used to initialize all physical objects at program startup,
 * holds references and performs operations on physicalObjects
 */
public class PhysicalObjectsRepository {
    private Register cashRegister;
    private Printer printer;
    private final Store store;
    private static PhysicalObjectsRepository instance;


    /**
     * Singleton method used to create an instance of the class
     * and make sure that multiple instances can not be created
     * <code> synchronized </code> keyword is used to make the
     * calls to the method thread safe.
     * * @return
     */
    public static PhysicalObjectsRepository getInstance() {
        if (instance == null) {
            synchronized (PhysicalObjectsRepository.class) {
                if (instance == null) {
                    instance = new PhysicalObjectsRepository();
                }
            }
        }
        return instance;
    }

    /**
     * Start up the register by loading it from the database
     * and add implementations of <code> EventObservers </code>
     * to it.
     *
     * {@Throws SystemStartUpFailureException} if the register can not be loaded from the database.
     * The exception is caught and sent to the exception handler in the calling <code> Main </code> class.
     *
     * @param eventObservers implementations of the eventObserver interface.
     *                       these implementations listen to changes in the
     *                       register model and update the view accordingly.
     */
    public void startUpRegister(ArrayList<EventObserver> eventObservers) {
        if (cashRegister == null) {
            try {
                cashRegister = (Register) IntegrationFactory.REGISTER_BALANCE_ACCOUNT.getDataBaseHandler().collect("RegisterOne");
            } catch (NotFoundException | DataBaseAccessFailureException e) {
                throw new SystemStartUpFailureException("Failed to startup the program : ", e);
            }
            cashRegister.setObservers(eventObservers);
        }
    }

    public void startUpPrinter() {
        printer = Printer.getInstance();
    }

    public Printer getPrinter() {
        return printer;
    }

    private PhysicalObjectsRepository() {
        Address address = new Address("Store street 123", "Store City", "Sweden", "191 49");
        ContactInformation contactInformation = new ContactInformation("spof@kth.se", "070 123 4567");
        store = new Store(address, contactInformation);
    }

    /**
     * Prints the receipt
     *
     * @param sale
     */
    public void printReceipt(SaleTransaction sale) {
        Printer.getInstance().print(new ReceiptDTO(store, sale));
    }

    public Register getCashRegister() {
        return cashRegister;
    }

    public Store getStore() {
        return store;
    }
}

package integration;
import model.discount.discounttypes.pricediscount.PriceDiscount;
import util.datatransferobject.ReceiptDTO;
import model.physicalobjects.Store;
import model.sale.Sale;

public class Printer {
    private static Printer instance;

    private Printer() {
    }

    /**
     * Print what is specified in the <code> receipt dto </code>
     * <p>
     * call to <code> formatReceipt </code> to get the right format.
     *
     * @param receiptDTO
     */
    public void print(ReceiptDTO receiptDTO) {
        // Print the receipt
    }

    private void formatReceipt(Store store, Sale sale) {

    }

    /**
     *  Singleton method used to create an instance of the class
     *  and make sure that multiple instances can not be created
     *  <code> synchronized </code> keyword is used to make the
     *  calls to the method thread safe.
     * * @return
     */
    public static Printer getInstance() {
        if(instance == null){
            synchronized (Printer.class) {
                if(instance == null){
                    instance = new Printer();
                }
            }
        }
        return instance;
    }
}
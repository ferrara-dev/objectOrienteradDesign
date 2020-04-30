package integration;
import util.datatransferobject.ReceiptDTO;
import model.physicalobjects.Store;
import model.sale.Sale;

public class Printer {

    public Printer(){

    }

    /**
     * Print what is specified in the <code> receipt dto </code>
     *
     * call to <code> formatReceipt </code> to get the right format.
     * @param receiptDTO
     */
    public void print(ReceiptDTO receiptDTO){
        // Print the receipt
    }

    private void formatReceipt(Store store, Sale sale){

    }
}

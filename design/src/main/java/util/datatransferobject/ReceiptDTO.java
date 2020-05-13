package util.datatransferobject;

import model.physicalobjects.Store;
import model.sale.saleinformation.SaleTransaction;

public class ReceiptDTO implements DataTransferObject{
    SaleTransaction saleTransaction;
    Store store;

    public ReceiptDTO(Store store, SaleTransaction saleTransaction){
        this.store = store;
        this.saleTransaction = saleTransaction;
    }
}

package util.datatransferobject;

import model.physicalobjects.Store;
import model.amount.Cost;
import model.sale.Date;
import model.sale.Sale;
import model.sale.SaleItem;
import java.util.ArrayList;

public class ReceiptDTO {
    double cashBack;
    Cost cost;
    ArrayList<SaleItem> items;
    Date   dateOfSale;
    String storeAddress;
    String storeName;

    public ReceiptDTO(Store store, Sale sale){
        cashBack = sale.getCashBack();
        cost = sale.getCost();
        items = sale.getCart().getItems();
        dateOfSale = sale.getSaleDetail().getDateOfSale();
        storeAddress = store.getStoreAddress().toString();
        storeName = store.getName();
    }
}

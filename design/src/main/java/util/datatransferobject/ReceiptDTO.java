package util.datatransferobject;

import model.physicalobjects.Store;
import model.sale.Date;
import model.sale.SaleItem;
import model.sale.saleinformation.ProductCart;
import model.sale.saleinformation.cost.CostDetail;
import model.transaction.saleTransaction.SaleTransaction;

import java.util.ArrayList;

public class ReceiptDTO implements DataTransferObject{
    double cashBack;
    CostDetail cost;
    ProductCart productCart;
    Date   dateOfSale;
    String storeAddress;
    String storeName;

    public ReceiptDTO(Store store, SaleTransaction sale){

    }
}

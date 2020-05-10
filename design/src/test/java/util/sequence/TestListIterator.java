package util.sequence;
import integration.DataBaseHandler;
import integration.productdb.Product;
import integration.productdb.ProductRepository;
import model.sale.SaleItem;
import org.junit.Assert;
import org.junit.Test;
import util.exception.notfoundexception.NotFoundException;

import java.util.ArrayList;

public class TestListIterator {

    @Test
    public void testSequence() throws NotFoundException {
        DataBaseHandler<Product,Object> productRepository = ProductRepository.getInstance();
        ArrayList<SaleItem> saleItems = new ArrayList<>();
        for(int i = 1; i<7; i++)
        {
            saleItems.add(new SaleItem(productRepository.collect(String.valueOf(i)),1));
        }

        ListSequence<SaleItem> listSequence = new ListSequence<>();
        listSequence.setItems(saleItems);
        ListSequenceIterator <SaleItem> listIterator = (ListSequenceIterator) listSequence.getSequenceIterator();
        SaleItem saleItem = listIterator.getCurrentItem();
        Assert.assertSame(saleItem,saleItems.get(0));
    }
}

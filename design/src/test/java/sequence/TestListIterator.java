package sequence;
import integration.productdb.ProductRepository;
import model.sale.SaleItem;
import org.junit.Test;

import java.util.ArrayList;

public class TestListIterator {

    @Test
    public void testSequence(){
        ProductRepository productRepository = new ProductRepository();
        ArrayList<SaleItem> saleItems = new ArrayList<>();
        for(int i = 1; i<7; i++)
        {
            saleItems.add(new SaleItem(productRepository.collect(String.valueOf(i)),1));
        }

        ListSequence<SaleItem> listSequence = new ListSequence<>();


    }
}

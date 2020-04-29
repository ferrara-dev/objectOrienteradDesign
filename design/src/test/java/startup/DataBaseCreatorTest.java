package startup;

import integration.DataBaseHandler;
import integration.productdb.InventoryHandler;
import junit.framework.TestCase;
import integration.productdb.Product;
import org.junit.Test;

public class DataBaseCreatorTest extends TestCase {

    @Test
    public void testMain(){
        DataBaseHandler<Product,Integer> handler = new InventoryHandler();
        Main.initDB();
        // Product [] products  = new Product[7];
        for(int i = 1; i < 8; i++){
            boolean isProduct = (handler.collect(String.valueOf(i)) != null);
            assertTrue(isProduct);
        }

    }
}
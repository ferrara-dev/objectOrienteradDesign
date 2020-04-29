package Integration;

import integration.DataBaseHandler;
import integration.productdb.InventoryHandler;
import integration.productdb.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductDBTest {

    @Test
    public void testProductRegistryHandler(){
        DataBaseHandler<Product,Integer> dataBaseHandler = new InventoryHandler();
        Product product = dataBaseHandler.collect("1");
        assertNotNull(product);
        assertEquals(product.getItemId(),1);
        assertEquals(product.getClass(),Product.class);
    }
}

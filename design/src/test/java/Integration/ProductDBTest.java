package Integration;

import integration.DataBaseHandler;
import integration.productdb.ProductRepository;
import integration.productdb.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductDBTest {

    @Test
    public void testProductRegistryHandler(){
        DataBaseHandler<Product,Object> dataBaseHandler = new ProductRepository();
        Product product = dataBaseHandler.collect("1");
        assertNotNull(product);
        assertEquals(product.getItemId(),1);
        assertEquals(product.getClass(),Product.class);
    }
}

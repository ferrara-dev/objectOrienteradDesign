package startup;

import integration.DataBaseHandler;
import integration.productdb.ProductRepository;
import junit.framework.TestCase;
import integration.productdb.Product;
import org.junit.Test;
import util.exception.notfoundexception.NotFoundException;

public class DataBaseCreatorTest extends TestCase {

    @Test
    public void testMain() throws NotFoundException {
        DataBaseHandler<Product,Object> handler = ProductRepository.getInstance();
        Main.initDB();
        // Product [] products  = new Product[7];
        for(int i = 1; i < 8; i++){
            boolean isProduct = (handler.collect(String.valueOf(i)) != null);
            assertTrue(isProduct);
        }

    }
}
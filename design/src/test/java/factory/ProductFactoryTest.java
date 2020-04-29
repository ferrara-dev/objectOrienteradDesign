package factory;

import util.datatransferobject.ItemDTO;
import integration.productdb.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductFactoryTest {

    @Test
    public void testProductFactory(){
        Factory<Product, ItemDTO> factory = new ProductFactory();
        assertNotNull(factory);

        Product product = factory.create(new ItemDTO("1",2,"3",4,5,6));

        assertNotNull(product);
        assertEquals(product.getItemId(),4);
        assertEquals(product.getCategory(),"3");
        assertEquals(product.getTaxRate(), 6,0000000);

    }
}
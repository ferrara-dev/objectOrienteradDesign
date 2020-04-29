package factory;

import util.datatransferobject.ItemDTO;
import integration.productdb.Product;

/**
 * Factory used to create and set attributes to a <code> Product </code>
 * Json object before storing it to the database.
 */
public class ProductFactory implements Factory <Product, ItemDTO>{

    @Override
    public Product create(ItemDTO itemDTO) {
        Product product = new Product();
        product.setAttributes(itemDTO);
        return product;
    }

}

package factory;

import integration.productdb.Product;
import model.customer.Member;
import util.datatransferobject.CustomerDTO;
import util.datatransferobject.ItemDTO;

/**
 * Enum implementation of a factory pattern used to create bean models
 * from implementations of the <code> DataTransferObject </code> interface
 */
public enum BeanModelFactorySupplier {
    PRODUCT {
        public Factory<Product, ItemDTO> getFactory() {
            return new ProductFactory();
        }
    },
    MEMBER {
        @Override
        public Factory<Member, CustomerDTO> getFactory() {
            return new CustomerFactory();
        }
    };

    public abstract Factory<?, ?> getFactory();
}

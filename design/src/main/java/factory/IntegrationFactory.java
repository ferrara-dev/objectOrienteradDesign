package factory;

import integration.DataBaseHandler;
import integration.customerdb.CustomerRepository;
import integration.discountdb.DiscountRepository;
import integration.productdb.ProductRepository;
import integration.saledb.RegisterBalanceAccount;
import integration.saledb.SaleLogHandler;

/**
 * Enum implementation used to reduce dependencies from the service layer to the integration layer,
 * by providing singleton implementations of the <code> DataBaseHandler </code> interface.
 *
 * All classes implementing <code> DataBaseHandler </code> can be collected using this enum class.
 */
public enum IntegrationFactory {

    REGISTER_BALANCE_ACCOUNT{
        @Override
        public DataBaseHandler getDataBaseHandler() {
            return RegisterBalanceAccount.getInstance();
        }
    },
    SALE_LOG{
        @Override
        public DataBaseHandler getDataBaseHandler() {
            return SaleLogHandler.getInstance();
        }
    },
    PRODUCT_REPO{
        @Override
        public DataBaseHandler getDataBaseHandler() {
            return ProductRepository.getInstance();
        }
    },
    DISCOUNT_REPO{
        @Override
        public DataBaseHandler getDataBaseHandler() {
            return DiscountRepository.getInstance();
        }
    },
    CUSTOMER_REPO{
        @Override
        public DataBaseHandler getDataBaseHandler() {
            return CustomerRepository.getInstance();
        }
    };


    public abstract DataBaseHandler getDataBaseHandler();
}

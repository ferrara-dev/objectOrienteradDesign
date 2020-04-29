package service.modelservice.productservice;

import integration.DataBaseHandler;
import integration.productdb.Product;
import service.IntegrationService;


public class ProductDBService implements IntegrationService<Product> {
    public static final Long HASH_KEY_ID = 122L;

    DataBaseHandler<Product, Product> dataBaseHandler;

    public ProductDBService(DataBaseHandler dataBaseHandler) {
        this.dataBaseHandler = dataBaseHandler;
    }

    @Override
    public void updateDB(Object object) {
        int stockstatus = ((Product) object).getStockstatus();
        int id = ((Product) object).getItemId();
        dataBaseHandler.register(String.valueOf(id), (Product) object);
    }

    @Override
    public Product getFromDB(Object object) {
        String itemId = Integer.toString((int) object);
        Product product = dataBaseHandler.collect(itemId);
        return product;
    }

    @Override
    public boolean find(Object object) {
        return false;
    }
}

package service.modelservice.saleservice;

import integration.DataBaseHandler;
import model.sale.Sale;
import service.IntegrationService;

public class SaleDBService implements IntegrationService<Sale> {
    public static final Long HASH_KEY_ID = 121L;
    DataBaseHandler<String, Sale> dataBaseHandler;
    public SaleDBService(DataBaseHandler dataBaseHandler) {
        this.dataBaseHandler = dataBaseHandler;
    }

    /**
     * Logs a completed sale to the SaleLog by calling
     * <code> SaleLogHandler </code> implementation of
     * <code> DataBaseHandler </code> interface.
     *
     * The sale is stored as an Json object with its
     * <Code> saleId </Code> attribute as primary key
     * in a sql database.
     * @param obj
     */
    @Override
    public void updateDB(Object obj) {
        if(obj instanceof Sale) {
            Sale sale = (Sale) obj;
            dataBaseHandler.register(sale.getSaleDetail().getSaleId().getValue(),sale );
        }
    }

    /**
     * TODO: implement functionality to collect
     *       completed sales
     * @param object sale id to collect the correct
     *               sale from the database.
     * @return the collected sale
     */
    @Override
    public Sale getFromDB(Object object) {
        return null;
    }

    @Override
    public boolean find(Object object) {
        return false;
    }
}

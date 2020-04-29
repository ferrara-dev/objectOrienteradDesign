package Integration;

import integration.DataBaseHandler;
import integration.saledb.SaleLogHandler;
import model.sale.Sale;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleDBTest {

    @Test
    public void testSaleLogHandler(){
        /* 1 */
        DataBaseHandler<Sale,Object> dataBaseHandler = new SaleLogHandler();
        Sale sale = new Sale();
        sale.startSale();
        sale.getSaleDetail().createDefault();
        sale.getSaleDetail().setCompleted(true);
        sale.getSaleDetail().setActive(false);
        String saleID = sale.getSaleDetail().getSaleId().getValue();

        dataBaseHandler.register(saleID,sale);
        Sale collectedSale = dataBaseHandler.collect(saleID);

        assertNotEquals(sale,collectedSale);
        assertEquals(saleID, collectedSale.getSaleDetail().getSaleId().getValue());

    }
}

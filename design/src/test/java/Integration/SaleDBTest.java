package Integration;

import org.junit.Test;

public class SaleDBTest {

    @Test
    public void testSaleLogHandler(){
        /* 1 */
        /*
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

         */

    }
}

package Integration;

import factory.IntegrationFactory;
import integration.DataBaseHandler;
import model.sale.saleinformation.SaleTransaction;
import org.junit.Test;
import service.ServiceFacade;
import startup.layer.ServiceCreator;
import util.datatransferobject.SaleTransactionDTO;

import static junit.framework.TestCase.assertEquals;

public class SaleDBTest {

    @Test
    public void testSaleLogHandler(){
        ServiceFacade serviceFacade = new ServiceFacade(new ServiceCreator());
        DataBaseHandler<SaleTransaction,Object> dataBaseHandler = IntegrationFactory.SALE_LOG.getDataBaseHandler();
        serviceFacade.startSale();
        SaleTransaction saleTransaction = serviceFacade.getSaleService().getSaleInformation();
        String saleID = saleTransaction.getSaleSpecification().getSaleId().getValue();
        SaleTransactionDTO saleTransactionDTO = new SaleTransactionDTO(saleTransaction);
        dataBaseHandler.register(saleID,saleTransaction);
        SaleTransaction collectedSale = dataBaseHandler.collect(saleID);
        assertEquals(collectedSale.getSaleSpecification().getSaleId().getValue(),saleID);
    }
}

package Integration;

import integration.DataBaseHandler;
import integration.saledb.SaleLogHandler;
import org.junit.Assert;
import org.junit.Test;

public class TestSingeltonPattern {
    @Test
    public void testSingletons(){
        DataBaseHandler dataBaseHandler1 = SaleLogHandler.getInstance();
        DataBaseHandler dataBaseHandler2 = SaleLogHandler.getInstance();

        Assert.assertSame(dataBaseHandler1,dataBaseHandler2);


    }
}

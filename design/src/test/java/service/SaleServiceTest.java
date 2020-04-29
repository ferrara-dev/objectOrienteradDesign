package service;

import org.junit.Before;
import org.junit.Test;
import service.modelservice.saleservice.SaleService;
import startup.layercreator.ServiceCreator;


import static org.junit.Assert.*;
public class SaleServiceTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testStartSale() {
        SaleService saleService = new SaleService(new ServiceCreator(new PhysicalObjectsRepository()));
        saleService.startSale();
        saleService.initSaleDefault();
        boolean isActive = saleService.getSale().getSaleDetail().isActive();
        assertTrue(isActive);

    }


}

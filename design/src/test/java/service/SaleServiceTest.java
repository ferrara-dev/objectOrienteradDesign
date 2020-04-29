package service;

import org.junit.Before;
import org.junit.Test;
import service.modelservice.saleservice.SaleService;
import startup.layer.ServiceCreator;
import util.exception.NotFoundException;


import static org.junit.Assert.*;
public class SaleServiceTest {
    SaleService saleService;
    @Before
    public void setUp() throws Exception {
        saleService = new SaleService(new ServiceCreator(new PhysicalObjectsRepository()));
    }

    @Test
    public void testStartSale() {
        SaleService saleService = new SaleService(new ServiceCreator(new PhysicalObjectsRepository()));
        saleService.startSale();
        saleService.initSaleDefault();
        boolean isActive = saleService.getSale().getSaleDetail().isActive();
        assertTrue(isActive);
    }

    @Test
    public void testEndSale() {

    }

    @Test
    public void testRegisterProduct() {
        saleService.startSale();
        saleService.initSaleDefault();
        boolean isActive = saleService.getSale().getSaleDetail().isActive();
        assertTrue(isActive);

        saleService.registerProduct(1,2);

        boolean cartContainsItem = saleService.getSale().getCart().contains(1);
        assertTrue(cartContainsItem);

        int quantity = saleService.getSale().getCart().getItem(1).getQuantity();
        assertEquals(2,quantity);

        saleService.registerProduct(3,2);
        saleService.registerProduct(1,3);

        int increasedQuantity = saleService.getSale().getCart().getItem(1).getQuantity();
        assertEquals(5,increasedQuantity);

        int itemListSize = saleService.getSale().getCart().getItems().size();
        assertEquals(2,itemListSize);
    }

    @Test (expected = NotFoundException.class)
    public void testRegisterInvalidProductId(){
        SaleService saleService = new SaleService(new ServiceCreator(new PhysicalObjectsRepository()));
        saleService.startSale();
        saleService.initSaleDefault();
        boolean isActive = saleService.getSale().getSaleDetail().isActive();
        assertTrue(isActive);

        saleService.registerProduct(99,2);
    }

}

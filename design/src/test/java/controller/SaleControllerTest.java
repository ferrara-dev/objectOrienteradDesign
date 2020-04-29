package controller;

import controller.subcontroller.SaleController;
import model.sale.Sale;
import org.junit.Before;
import org.junit.Test;
import service.PhysicalObjectsRepository;
import startup.RootCreator;
import startup.layer.ServiceCreator;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class SaleControllerTest {
    SaleController saleController;
    @Before
    public void startup(){
        ServiceCreator serviceCreator = new ServiceCreator(new PhysicalObjectsRepository());
        saleController = new SaleController(serviceCreator.getSaleService());
    }

    @Test
    public void testSaleController(){
        // assert that a new instance of sale is created
        saleController.startSale();
        Sale sale = saleController.getSaleService().getSale();
        assertNotNull(sale);
        saleController.endSale();
        // assert that the new instance is created
        saleController.startSale();
        Sale newSale = saleController.getSaleService().getSale();
        String firstSaleId = sale.getSaleDetail().getSaleId().getValue();
        String secondSaleId = newSale.getSaleDetail().getSaleId().getValue();
        assertNotEquals(firstSaleId,secondSaleId);
    }

}

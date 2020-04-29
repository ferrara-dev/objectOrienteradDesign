package mainoperations;


import controller.MainController;
import controller.subcontroller.PaymentController;
import controller.subcontroller.SaleController;
import org.junit.Before;
import org.junit.Test;
import service.PhysicalObjectsRepository;
import service.modelservice.saleservice.SaleService;
import startup.layer.ControllerCreator;
import startup.layer.ServiceCreator;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;


public class TestStartSale {
    ControllerCreator creator;
    MainController mainController;
    SaleController saleController;
    PaymentController paymentController;

    @Before
    public void startup(){
       creator = new ControllerCreator(new ServiceCreator(new PhysicalObjectsRepository()));
       mainController = creator.getMainController();
       saleController = creator.getSaleController();
       paymentController = creator.getPaymentController();
    }

    @Test
    public void testStartSaleOperation(){
        saleController.startSale();
        SaleService saleService = saleController.getSaleService();

        boolean saleIsActive = saleService.getSale().getSaleDetail().isActive();
        assertTrue(saleIsActive);
        boolean saleIsCompleted = saleService.getSale().getSaleDetail().isCompleted();
        assertFalse(saleIsCompleted);
    }

}

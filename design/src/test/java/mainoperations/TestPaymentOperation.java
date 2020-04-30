package mainoperations;

import controller.MainController;
import controller.subcontroller.PaymentController;
import controller.subcontroller.SaleController;
import model.sale.Sale;
import org.junit.Before;
import org.junit.Test;
import service.PhysicalObjectsRepository;
import service.modelservice.saleservice.SaleService;
import startup.layer.ControllerCreator;
import startup.layer.ServiceCreator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestPaymentOperation {
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
        saleController.startSale();
    }

    @Test
    public void testPaymentOperation(){
        saleController.startSale();
        SaleService saleService = saleController.getSaleService();
        double initialToPay = saleService.getSaleCost().getNumber().doubleValue();

        assertEquals(initialToPay,0, 0);

        saleService.registerProduct(3,1);

        double newToPay = saleService.getSaleCost().getNumber().doubleValue();
        assertNotEquals(initialToPay,newToPay);

        Sale sale = saleService.getSale();
        assertEquals(sale.getCost().getTotalCost(),newToPay,0);

        int itemQuantity = sale.getCart().getItem(3).getQuantity();

        saleService.registerProduct(3,55);

        int newQuantity = sale.getCart().getItem(3).getQuantity();

        assertNotEquals(itemQuantity,newQuantity);
        assertEquals(newQuantity, 56);
        double toPay = sale.getCost().getNumber();
        System.out.println(toPay);
        paymentController.initiatePaymentProcess(2500);

        double updatedToPay = sale.getCost().getNumber();
        assertEquals(20,updatedToPay,0);
        int oldStockStatus = saleService.getProductService().getProduct(3).getStockstatus();

        paymentController.initiatePaymentProcess(20);
        double furtherUpdatedToPay = sale.getCost().getNumber();
        assertEquals(0,furtherUpdatedToPay,0);
        int updateStockStatus = saleService.getProductService().getProduct(3).getStockstatus();

        assertNotEquals(oldStockStatus,updateStockStatus);


    }

}

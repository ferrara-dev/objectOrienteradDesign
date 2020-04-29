package controller;
import org.junit.Before;
import org.junit.Test;
import startup.RootCreator;



import static org.junit.Assert.*;
public class MainControllerTest {
    RootCreator rootCreator;
    MainController mainController;

    @Before
    public void startup(){
        rootCreator = new RootCreator();
        rootCreator.initServiceLayer();
        rootCreator.initControllerLayer();
        mainController = rootCreator.getControllerCreator().getMainController();
    }

    @Test
    public void testMainController(){
        assertNotNull(mainController.getCustomerController());
        assertNotNull(mainController.getDiscountController());
        assertNotNull(mainController.getPaymentController());
        assertNotNull(mainController.getSaleController());
    }
}

package mainoperations;


import controller.MainController;

import org.junit.Before;
import org.junit.Test;

import startup.layer.ControllerCreator;
import startup.layer.ServiceCreator;



public class TestStartSale {
    ControllerCreator creator;
    MainController mainController;


    @Before
    public void startup(){
       creator = new ControllerCreator(new ServiceCreator());
       mainController = creator.getMainController();

    }

    @Test
    public void testStartSaleOperation(){

    }

}

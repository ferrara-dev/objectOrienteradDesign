package startup;

import com.alee.laf.WebLookAndFeel;
import com.alee.skin.dark.WebDarkSkin;
import factory.IntegrationFactory;
import model.physicalobjects.Register;
import exception.exceptionhandler.ExceptionHandler;

import util.datatransferobject.CustomerDTO;
import factory.CustomerFactory;
import factory.ProductFactory;
import util.datatransferobject.ItemDTO;
import startup.database.DataBaseCreator;
import startup.database.JsonObjectTableInitiator;
import exception.SystemStartUpFailureException;
import exception.notfoundexception.NotFoundException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Main {
    private static RootCreator rootCreator;

    public static void main(String[] args) throws NotFoundException {
        initDB();
        start();
    }

    public static void start()  {
        try {
            rootCreator = new RootCreator();
            rootCreator.createGui();
            rootCreator.initExceptionHandler();
            rootCreator.initServiceLayer();
            rootCreator.initControllerLayer();
            rootCreator.initPeripherals();
        } catch (SystemStartUpFailureException systemStartUpFailureException){
            ExceptionHandler.handle(systemStartUpFailureException);
        }
        run();
    }

    private static void run() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    WebLookAndFeel.install ( WebDarkSkin.class );
                    rootCreator.showGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );

    }

    public static void initDB() throws NotFoundException {

        DataBaseCreator dataBaseCreator = new DataBaseCreator();
        if (dataBaseCreator.createTable()) {
            Register register = new Register();
            register.setDefault();
            IntegrationFactory.REGISTER_BALANCE_ACCOUNT.getDataBaseHandler().register("RegisterOne",register);

            JsonObjectTableInitiator jsonObjectTableInitiator =
                    new JsonObjectTableInitiator("ProductDB","jsonProductTable");

            for (int i = 1; i < 8; i++) {
                ItemDTO itemDTO = (ItemDTO) jsonObjectTableInitiator.collect(String.valueOf(i));
                jsonObjectTableInitiator.register(String.valueOf(i), new ProductFactory().create(itemDTO));
            }

            jsonObjectTableInitiator = new JsonObjectTableInitiator("CustomerDB","jsonCustomerTable");
            ArrayList<CustomerDTO> customerDTOS = (ArrayList<CustomerDTO>) jsonObjectTableInitiator.collect("All");
            for(CustomerDTO customerDTO: customerDTOS){
                jsonObjectTableInitiator.register(customerDTO.getCustomerId(), new CustomerFactory().create(customerDTO));
            }

        }
    }

    public static class TimedExit {
       private static Timer timer = new Timer();
       private static TimerTask exitApp = new TimerTask() {
            public void run() {
                System.exit(0);
            }
        };

        public static void exit(){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(1);
                }
            }).start();

        }
    }
}

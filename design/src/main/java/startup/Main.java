package startup;

import com.alee.laf.WebLookAndFeel;
import com.alee.skin.dark.WebDarkSkin;
import util.datatransferobject.CustomerDTO;
import factory.CustomerFactory;
import factory.ProductFactory;
import util.datatransferobject.ItemDTO;
import startup.databasecreator.DataBaseCreator;
import startup.databasecreator.JsonObjectTableInitiator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class Main {
    private static RootCreator rootCreator;
    public static void main(String[] args) {
        initDB();
        start();
    }


    public static void start()  {
        rootCreator = new RootCreator();
        rootCreator.initServiceLayer();
        rootCreator.initControllerLayer();
        rootCreator.createGui();

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
        });

    }

    public static void initDB() {
        DataBaseCreator dataBaseCreator = new DataBaseCreator();
        if (dataBaseCreator.createTable()) {

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

            jsonObjectTableInitiator = new JsonObjectTableInitiator("DiscountDB", "jsonDiscountTable");
        }
    }
}

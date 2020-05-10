package integration.discountdb;

import integration.customerdb.CustomerRepository;
import util.datatransferobject.DiscountDTO;
import integration.DataBaseHandler;
import model.discount.Discount;
import util.exception.notfoundexception.NotFoundException;

import java.sql.*;
import java.util.ArrayList;

public class DiscountRepository implements DataBaseHandler<ArrayList, Discount> {
    private final String SELECT_DISCOUNT_TEMPLATE = "SELECT * FROM %s ;";
    private static DiscountRepository instance;

    /**
     *  Singleton method used to create an instance of the class
     *  and make sure that multiple instances can not be created
     *  <code> synchronized </code> keyword is used to make the
     *  calls to the method thread safe.
     * * @return
     */
    public static DataBaseHandler<ArrayList, Discount> getInstance() {
        if(instance == null){
            synchronized (CustomerRepository.class) {
                if(instance == null){
                    instance = new DiscountRepository();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean register(String id, Discount discount) {
        /*
            TODO: Använda rabatter mappas till kundID i en
             sql tabell så att de inte kan användas flera gånger
             under samma dag.
         */
        return false;
    }

    /**
     * Overide to find a customer and check if valid for a discount
     *
     * @param id the customers identification
     * @return
     */

    @Override
    public boolean find(String id){
        try (Connection con = DriverManager.getConnection(URL)) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_TEMPLATE, "jsonCustomerTable", id));
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            DataBaseHandler.printSQLException(ex);
        }

        throw new NotFoundException();
    }

    /**
     * Override to collect all discounts available on a specific day
     *
     * @param dayOfTheWeek
     * @return
     */

    @Override
    public ArrayList<DiscountDTO> collect(String dayOfTheWeek){
        try (Connection con = DriverManager.getConnection(URL)) {
            ArrayList<DiscountDTO> discountDTOList = new ArrayList<>();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_DISCOUNT_TEMPLATE, "DiscountDB"));

            while (rs.next()) {
                String available = rs.getString("available");
                String[] dates = available.split(":");
                boolean isAvailable = false;
                for (String date : dates) {
                    if (date.equals(dayOfTheWeek)) {
                        isAvailable = true;
                        break;
                    }
                }

                if (isAvailable) {
                    String itemId = rs.getString("id");
                    String type = rs.getString("type");
                    String requirement = rs.getString("requierment");
                    String reduction = rs.getString("reduction");
                    String limit = rs.getString("limitation");
                    DiscountDTO discountDTO = new DiscountDTO(type, requirement, reduction, limit, itemId);
                    discountDTOList.add(discountDTO);
                } else
                    continue;
            }

            return discountDTOList;
        } catch (SQLException ex) {
            DataBaseHandler.printSQLException(ex);
        }

        throw new NotFoundException("Item not found");
    }


}

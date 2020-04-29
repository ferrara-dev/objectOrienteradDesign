package integration.discountdb;

import util.datatransferobject.DiscountDTO;
import integration.DataBaseHandler;
import model.discount.Discount;
import util.exception.NotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountRegistryHandler implements DataBaseHandler<List, Discount> {
    private final String SELECT_DISCOUNT_TEMPLATE = "SELECT * FROM %s ;";

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
    public boolean find(String id) {
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
    public List<DiscountDTO> collect(String dayOfTheWeek) {
        try (Connection con = DriverManager.getConnection(URL)) {
            List<DiscountDTO> discountDTOList = new ArrayList<>();
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

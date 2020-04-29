package startup.databasecreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.datatransferobject.CustomerDTO;
import integration.DataBaseHandler;
import util.datatransferobject.ItemDTO;
import util.exception.NotFoundException;
import util.Tax;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

public class JsonObjectTableInitiator implements DataBaseHandler {
    private final String jsonTableName;
    private final String tableName;

    public JsonObjectTableInitiator(final String tableName, final String jsonTableName) {
        this.jsonTableName = jsonTableName;
        this.tableName = tableName;
    }

    public boolean register(String id, Object obj) {
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();
            String value = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(obj).getBytes());
            // Step 3: Execute the query or update query
            int result = statement.executeUpdate(String.format(INSERT_TEMPLATE, jsonTableName, id, objectMapper.writeValueAsString(obj)));
            System.out.println("No. of records affected : " + result);
        } catch (JsonProcessingException jme) {
            System.err.println(jme.getMessage());
        } catch (SQLException e) {

            // print SQL exception information
            DataBaseHandler.printSQLException(e);
        }
        return true;
    }

    public boolean find(String id) {
        return false;
    }

    public Object collect(String id) {
        if (tableName == "ProductDB") {
            ItemDTO itemDTO = collectProduct(id);
            return itemDTO;
        } else if (tableName == "CustomerDB") {
            ArrayList<CustomerDTO> customerDTOs = collectCustomer();
            return customerDTOs;
        }  else
            throw new IllegalArgumentException();
    }

    private ArrayList<CustomerDTO> collectCustomer() {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL)) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_ALL_TEMPLATE, tableName));

            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("id");
                String email = rs.getString("email");
                CustomerDTO customerDTO = new CustomerDTO(name, id, email);
                customerDTOS.add(customerDTO);

            }
            return customerDTOS;

        } catch (SQLException ex) {
            DataBaseHandler.printSQLException(ex);
        }

        throw new NotFoundException("Item not found");
    }


    private ItemDTO collectProduct(String id) {
        try (Connection con = DriverManager.getConnection(URL)) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_TEMPLATE, tableName, id));

            while (rs.next()) {
                int itemId = Integer.parseInt(rs.getString("id"));
                int stockStatus = Integer.parseInt(rs.getString("stockstatus"));
                double price = Double.parseDouble(rs.getString("price"));
                String name = rs.getString("name");
                String category = rs.getString("category");


                ItemDTO itemDTO = new ItemDTO(name, price, category, itemId, stockStatus, Tax.getTax(category));
                return itemDTO;
            }

        } catch (SQLException ex) {
            DataBaseHandler.printSQLException(ex);
        }

        throw new NotFoundException("Item not found");
    }

}


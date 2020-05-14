package startup.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.AppProperties;
import util.datatransferobject.CustomerDTO;
import integration.DataBaseHandler;
import util.datatransferobject.ItemDTO;
import exception.ErrorId;
import exception.notfoundexception.NotFoundException;
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
        Connection con = null;
        try {
            String url = AppProperties.getDataBaseURL();
            con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();
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

    public Object collect(String id) throws NotFoundException {
        if (tableName == "ProductDB") {
            ItemDTO itemDTO = collectProduct(id);
            return itemDTO;
        } else if (tableName == "CustomerDB") {
            ArrayList<CustomerDTO> customerDTOs = collectCustomer();
            return customerDTOs;
        }  else
            throw new IllegalArgumentException();
    }

    private ArrayList<CustomerDTO> collectCustomer() throws NotFoundException {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        Connection con = null;
        try {
            String url = AppProperties.getDataBaseURL();
            con = DriverManager.getConnection(url);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_ALL_TEMPLATE, tableName));

            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("id");
                String email = rs.getString("email");
                CustomerDTO customerDTO = new CustomerDTO(name, id, email);
                customerDTOS.add(customerDTO);

            }


        } catch (SQLException ex) {
            DataBaseHandler.printSQLException(ex);
        }
        return customerDTOS;
    }


    private ItemDTO collectProduct(String id) throws NotFoundException {
        Connection con = null;
        try {
            String url = AppProperties.getDataBaseURL();
            con = DriverManager.getConnection(url);
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

        throw new NotFoundException("ID" + "\"" + id + "\"" , ErrorId.PRODUCT_ID_NOT_FOUND);
    }

}


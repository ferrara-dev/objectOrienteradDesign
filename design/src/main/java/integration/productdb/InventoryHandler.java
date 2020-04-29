package integration.productdb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import integration.DataBaseHandler;
import util.exception.NotFoundException;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.Base64;

public class InventoryHandler implements DataBaseHandler<Product, Object> {
    /**
     * Override to register a new product to the database
     *
     * @param id
     * @return
     */
    @Override
    public boolean register(String id, Object product) {
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();
            String value = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(product).getBytes());
            // Step 3: Execute the query or update query
            int result = statement.executeUpdate(String.format(UPDATE_TEMPLATE, "jsonProductTable", objectMapper.writeValueAsString(product), id));
            System.out.println("No. of records affected : " + result);
        } catch (JsonProcessingException jme) {
            System.err.println(jme.getMessage());
        } catch (SQLException e) {

            // print SQL exception information
            DataBaseHandler.printSQLException(e);
        }
        return true;
    }

    /**
     * Override to check if a product exists in the database
     *
     * @param id
     * @return
     */
    @Override
    public boolean find(String id) {
        try (Connection con = DriverManager.getConnection(URL)) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_TEMPLATE, "ProductDB", id));

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            DataBaseHandler.printSQLException(ex);
        }
        return false;
    }

    /**
     * Override to collect a product from the database
     *
     * The valid product identifications are 1,2,3,4,5,6,7
     * @param id the product id
     * @return
     */
    @Override
    public Product collect(String id) {
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();

            // Step 3: Execute the query or update query
            ResultSet result = statement.executeQuery(String.format(SELECT_TEMPLATE, "jsonProductTable", id));
            while (result.next()) {
                Reader reader = result.getClob(2).getCharacterStream();
                StringBuilder buffer = new StringBuilder();
                int numCharsRead;
                char[] arr = new char[8 * 1024];
                while ((numCharsRead = reader.read(arr, 0, arr.length)) != -1) {
                    buffer.append(arr, 0, numCharsRead);
                }
                reader.close();
                String targetString = buffer.toString();

                return objectMapper.readValue(targetString, Product.class);
            }


        } catch (SQLException e) {

            // print SQL exception information
            DataBaseHandler.printSQLException(e);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NotFoundException("Product not found!");
    }
}


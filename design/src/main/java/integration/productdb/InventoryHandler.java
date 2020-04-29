package integration.productdb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import integration.DataBaseHandler;
import util.exception.NotFoundException;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class InventoryHandler implements DataBaseHandler<Product, Integer> {
    /**
     * Override to register
     *
     * @param id
     * @param stockstatus
     * @return
     */
    @Override
    public boolean register(String id, Integer stockstatus) {
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();

            // Step 3: Execute the query or update query
            int result = statement.executeUpdate(String.format(UPDATE_TEMPLATE, "ProductDB", stockstatus, id));
            return true;

        } catch (SQLException e) {

            // print SQL exception information
            DataBaseHandler.printSQLException(e);
        }
        throw new NotFoundException("SaleDetail not found!");
    }

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


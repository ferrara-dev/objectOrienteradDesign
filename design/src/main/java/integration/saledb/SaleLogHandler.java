package integration.saledb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import integration.DataBaseHandler;
import integration.customerdb.CustomerRepository;
import integration.discountdb.DiscountRepository;
import model.discount.Discount;
import model.sale.Sale;
import util.exception.NotFoundException;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.Base64;
import java.util.List;

public class SaleLogHandler implements DataBaseHandler<Sale,Object> {
   private final String saleDB = "sales";
    private static SaleLogHandler instance;


    /**
     *  Singleton method used to create an instance of the class
     *  and make sure that multiple instances can not be created
     *  <code> synchronized </code> keyword is used to make the
     *  calls to the method thread safe.
     * * @return
     */
    public static DataBaseHandler<Sale,Object> getInstance() {
        if(instance == null){
            synchronized (SaleLogHandler.class) {
                if(instance == null){
                    instance = new SaleLogHandler();
                }
            }
        }
        return instance;
    }

    /**
     * Override to register an instance of <code> Sale </code> as
     * an JSON object to the sales database.
     * @param id
     * @param sale
     */
    @Override
    public boolean register(String id, Object sale) {
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();
            String value = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(sale).getBytes());
            // Step 3: Execute the query or update query
            int result = statement.executeUpdate(String.format(INSERT_TEMPLATE,saleDB,id, objectMapper.writeValueAsString(sale)));
            System.out.println("No. of records affected : " + result);
        } catch (JsonProcessingException jme) {
            System.err.println(jme.getMessage());
        } catch (SQLException e) {

            // print SQL exception information
            DataBaseHandler.printSQLException(e);
        }
        return true;
    }


    @Override
    public boolean find(String id) {
        try (Connection con = DriverManager.getConnection(URL)){
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_TEMPLATE,saleDB,id));

            if(rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            DataBaseHandler.printSQLException(ex);
        }
        return false;
    }

    /**
     * Override to collect a specific sale from database.
     * Throws <code> NotFoundException </code> that is handled
     * in the view if sale with the specified identification
     * is not found in the database.
     * @param saleId a string that identifies
     *              the sale that is to be collected
     * @return the sale specified by the saleId
     */
    @Override
    public Sale collect(String saleId) {
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();

            // Step 3: Execute the query or update query
            ResultSet result = statement.executeQuery(String.format(SELECT_TEMPLATE,saleDB, saleId));
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

                return objectMapper.readValue(targetString, Sale.class);
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
        throw  new NotFoundException("Sale not found!");
    }
}

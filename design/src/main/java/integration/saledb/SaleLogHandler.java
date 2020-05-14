package integration.saledb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import integration.DataBaseHandler;
import exception.DataBaseAccessFailureException;
import exception.ErrorId;
import exception.notfoundexception.NotFoundException;
import model.sale.saleinformation.SaleTransaction;
import util.AppProperties;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.Base64;


public class SaleLogHandler implements DataBaseHandler<SaleTransaction,Object> {
   private final String saleDB = "sales";
   private static SaleLogHandler instance;


    /**
     *  Singleton method used to create an instance of the class
     *  and make sure that multiple instances can not be created
     *  <code> synchronized </code> keyword is used to make the
     *  calls to the method thread safe.
     * * @return
     */
    public static DataBaseHandler<SaleTransaction,Object> getInstance() {
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
        Connection con = null;
        try {
            String url = AppProperties.getDataBaseURL();
            con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();
            String value = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(sale).getBytes());
            // Step 3: Execute the query or update query
            int result = statement.executeUpdate(String.format(INSERT_TEMPLATE,saleDB,id, objectMapper.writeValueAsString(sale)));
            System.out.println("No. of records affected : " + result);
        } catch (JsonProcessingException jme) {
            throw new DataBaseAccessFailureException(jme,ErrorId.DATABASE_ACCESS_FAILURE);
        } catch (SQLException e) {
            DataBaseHandler.printSQLException(e);
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
        }
        return true;
    }


    @Override
    public boolean find(String id){
        Connection con = null;
        try {
            String url = AppProperties.getDataBaseURL();
            con = DriverManager.getConnection(url);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_TEMPLATE,saleDB,id));

            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
        }
        return false;
    }

    /**
     * Override to collect a specific sale from database.
     * Throws <code> NotFoundException </code> that is handled
     * in the view if sale with the specified identification
     * is not found in the database.
     * @param id a string that identifies
     *              the sale that is to be collected
     * @return the sale specified by the saleId
     */
    @Override
    public SaleTransaction collect(String id){
        Connection con = null;
        try {
            String url = AppProperties.getDataBaseURL();
            con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();

            // Step 3: Execute the query or update query
            ResultSet result = statement.executeQuery(String.format(SELECT_TEMPLATE,saleDB, id));
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

                return objectMapper.readValue(targetString, SaleTransaction.class);
            }
        } catch (SQLException e) {
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
        } catch (JsonParseException e) {
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
        } catch (JsonMappingException e) {
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
        } catch (IOException e) {
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
        }

        throw new NotFoundException("ID" + "\"" + id + "\"" , ErrorId.SALE_ID_NOT_FOUND);
    }
}

package integration.productdb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import integration.DataBaseHandler;
import util.exception.DataBaseAccessFailureException;
import util.exception.ErrorId;
import util.exception.notfoundexception.NotFoundException;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.Base64;

public class ProductRepository implements DataBaseHandler<Product, Object> {
    private static ProductRepository instance;

    private ProductRepository(){
    }

    /**
     * Override to register a new product to the database
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
        } catch (JsonProcessingException e) {
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
        } catch (SQLException e) {
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
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
        } catch (SQLException e) {
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
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
    public Product collect(String id){
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
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
        } catch (JsonParseException e) {
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
        } catch (JsonMappingException e) {
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
        } catch (IOException e) {
            throw new DataBaseAccessFailureException(e,ErrorId.DATABASE_ACCESS_FAILURE);
        }
        throw new NotFoundException(new IllegalArgumentException("id" + " \"" + id + "\""), ErrorId.PRODUCT_ID_NOT_FOUND);
    }

    /**
     *  Singleton method used to create an instance of the class
     *  and make sure that multiple instances can not be created
     *  <code> synchronized </code> keyword is used to make the
     *  calls to the method thread safe.
     * * @return
     */
    public static DataBaseHandler<Product, Object> getInstance() {
        if(instance == null){
            synchronized (ProductRepository.class) {
                if(instance == null){
                    instance = new ProductRepository();
                }
            }
        }
        return instance;
    }

    public static DataBaseHandler<Product, Object> getInstanceNotThreadSafe() {
        return new ProductRepository();
    }
}


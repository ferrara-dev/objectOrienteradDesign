package integration.saledb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import integration.Bank;
import integration.DataBaseHandler;
import model.banking.Balance;
import model.physicalobjects.Register;
import util.exception.notfoundexception.NotFoundException;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.Base64;

public class RegisterBalanceAccount implements Bank {
    private static RegisterBalanceAccount instance;
    private static String REGISTERACCOUNT = "RegisterAccount";

    private RegisterBalanceAccount() {

    }

    /**
     * Singleton method used to create an instance of the class
     * and make sure that multiple instances can not be created
     * <code> synchronized </code> keyword is used to make the
     * calls to the method thread safe.
     * * @return
     */
    public static Bank getInstance() {
        if (instance == null) {
            synchronized (RegisterBalanceAccount.class) {
                if (instance == null) {
                    instance = new RegisterBalanceAccount();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean register(String id, Object obj) {
        String sqlQuery = INSERT_TEMPLATE;

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();
            String value = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(obj).getBytes());
            // Step 3: Execute the query or update query
            int result;
            if (find(id)) {
                sqlQuery = UPDATE_TEMPLATE;
                 result = statement.executeUpdate(String.format(sqlQuery, REGISTERACCOUNT, objectMapper.writeValueAsString(obj), id));
            } else
                 result = statement.executeUpdate(String.format(sqlQuery, REGISTERACCOUNT, id, objectMapper.writeValueAsString(obj)));

            System.out.println("No. of records affected : " + result);
            return true;
        } catch (JsonProcessingException jme) {
            System.err.println(jme.getMessage());
        } catch (SQLException e) {
            // print SQL exception information
            DataBaseHandler.printSQLException(e);
        } catch (NotFoundException notFoundException) {
            notFoundException.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean find(String id){
        try (Connection con = DriverManager.getConnection(URL)) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_TEMPLATE, REGISTERACCOUNT, id));

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            DataBaseHandler.printSQLException(ex);
        }
        return false;
    }

    @Override
    public Register collect(String id){
        // Step 1: Establishing a Connection
        if(find(id)) {
            try (Connection connection = DriverManager.getConnection(URL)) {
                Statement statement = connection.createStatement();

                // Step 3: Execute the query or update query
                ResultSet result = statement.executeQuery(String.format(SELECT_TEMPLATE, REGISTERACCOUNT, id));
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
                    return objectMapper.readValue(targetString, Register.class);
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
        }

        throw new NotFoundException("No register with " + "\"" + id+ "\"" + " exist in the database");
    }

}

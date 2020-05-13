package integration.saledb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import integration.Bank;
import model.physicalobjects.Register;
import util.AppProperties;
import exception.DataBaseAccessFailureException;
import exception.ErrorId;
import exception.notfoundexception.NotFoundException;

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
        Connection con = null;
        try {
            String url = AppProperties.getDataBaseURL();
            con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();
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
        } catch (JsonProcessingException | SQLException e) {
            throw new DataBaseAccessFailureException("Failed to update register" , e, ErrorId.DATABASE_ACCESS_FAILURE);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                throw new DataBaseAccessFailureException("Failed to update register" , e, ErrorId.DATABASE_CLOSE_ON_EXIT_FAILURE);
            }
        }
    }

    @Override
    public boolean find(String id) {
        Connection con = null;
        try {
            String url = AppProperties.getDataBaseURL();
            con = DriverManager.getConnection(url);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_TEMPLATE, REGISTERACCOUNT, id));
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DataBaseAccessFailureException("Failed to connect to database" , e, ErrorId.DATABASE_ACCESS_FAILURE);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                throw new DataBaseAccessFailureException("Failed to close connection to database " , e, ErrorId.DATABASE_CLOSE_ON_EXIT_FAILURE);
            }
        }
        return false;
    }

    @Override
    public Register collect(String id) {
        // Step 1: Establishing a Connection
        if (find(id)) {
            Connection con = null;
            try {
                String url = AppProperties.getDataBaseURL();
                con = DriverManager.getConnection(url);
                Statement statement = con.createStatement();

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
                throw new DataBaseAccessFailureException("Failed to retrieve register balance" , e, ErrorId.DATABASE_ACCESS_FAILURE);
            } catch (JsonParseException e) {
                throw new DataBaseAccessFailureException("Failed to retrieve register balance"  , e, ErrorId.DATABASE_ACCESS_FAILURE);
            } catch (JsonMappingException e) {
                throw new DataBaseAccessFailureException("Failed to retrieve register balance" , e, ErrorId.DATABASE_ACCESS_FAILURE);
            } catch (IOException e) {
                throw new DataBaseAccessFailureException("Failed to retrieve register balance" , e, ErrorId.DATABASE_ACCESS_FAILURE);
            }finally {
                try {
                    if (con != null)
                        con.close();
                } catch (SQLException e) {
                    throw new DataBaseAccessFailureException("Failed to close connection to database "  , e, ErrorId.DATABASE_CLOSE_ON_EXIT_FAILURE);
                }
            }
        }
        throw new NotFoundException("ID" + "\"" + id + "\"", ErrorId.REGISTER_ID_NOT_FOUND);
    }

}

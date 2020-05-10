package integration.saledb;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import integration.Bank;
import integration.DataBaseHandler;
import model.banking.BankAccount;
import util.exception.notfoundexception.NotFoundException;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.Base64;

public class AccountingHandler implements Bank {
    private static AccountingHandler instance;
    private static String BANK = "bankDB";
    private static String CASH_REGISTER_LOG = "cashRegisterLog";

    private AccountingHandler() {

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
            synchronized (AccountingHandler.class) {
                if (instance == null) {
                    instance = new AccountingHandler();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean register(String id, Object obj) {
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();
            String value = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(obj).getBytes());
            // Step 3: Execute the query or update query
            int result = statement.executeUpdate(String.format(UPDATE_TEMPLATE, BANK, objectMapper.writeValueAsString(obj), id));
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
    public boolean find(String id){
        try (Connection con = DriverManager.getConnection(URL)) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_TEMPLATE, BANK, id));

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            DataBaseHandler.printSQLException(ex);
        }
        return false;
    }


    @Override
    public Object collect(String id){
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();

            // Step 3: Execute the query or update query
            ResultSet result = statement.executeQuery(String.format(SELECT_TEMPLATE, BANK, id));
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

                return objectMapper.readValue(targetString, BankAccount.class);
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

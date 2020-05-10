package integration.customerdb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import integration.DataBaseHandler;
import model.customer.Member;
import util.exception.notfoundexception.NotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;


public class CustomerRepository implements DataBaseHandler <Member, Member>{
    private static final String URL = "jdbc:h2:file:./userDB;DB_CLOSE_DELAY=-1";
    private static final String INSERT_MULTIPLE_USERS_SQL = "INSERT INTO  CustomerDB " +
            "VALUES ('940412-1395', 'Samuel', 'samuel@gmail.com', 'India', '123')," +
            "('960404-6541', 'Deepa', 'deepa@gmail.com', 'India', '123')," + "('711231-6325', 'Tom', 'top@gmail.com', 'India', '123');";
    private static final String SQL_FIND_USER = "SELECT * FROM CustomerDB WHERE id='%s';";
    private static final String SQL_FIND_USERNAME = "SELECT * FROM CustomerDB *;";

    private static CustomerRepository instance;

    private CustomerRepository(){

    }

    /**
     * Override to register a new member to the database.
     * @param id
     * @param member
     * @return
     */
    @Override
    public boolean register(String id, Member member) {

        return false;
    }

    /**
     * Override to check if a customer exists in the database
     * @param customerId
     * @return
     */
    public boolean find(String customerId){
        try (Connection con = DriverManager.getConnection(URL)) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(String.format(SELECT_TEMPLATE, "jsonCustomerTable", customerId));
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            DataBaseHandler.printSQLException(ex);
        }

        throw new NotFoundException();
    }

    /**
     * Override to collect a member object from the database
     * @param id
     * @return
     */
    @Override
    public Member collect(String id){
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();

            // Step 3: Execute the query or update query
            ResultSet result = statement.executeQuery(String.format(SELECT_TEMPLATE, "jsonCustomerTable", id));
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

                return objectMapper.readValue(targetString, Member.class);
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
        throw new NotFoundException("Customer not found!");
    }


    public static void readRecord(String tableName, String item, String key) {
        try (Connection con = DriverManager.getConnection(URL)) {
            Statement stm = con.createStatement();

            System.out.println("Reading data from " + tableName);
            stm = con.createStatement();
            String sql = "SELECT " + item + " FROM " + tableName + " WHERE id='%s'";
            ResultSet rs = stm.executeQuery(String.format(sql, key));

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                String name = rs.getString("name");
                // Display values
                System.out.print("User : " + name);
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    /**
     *  Singleton method used to create an instance of the class
     *  and make sure that multiple instances can not be created
     *  <code> synchronized </code> keyword is used to make the
     *  calls to the method thread safe.
     * * @return
     */
    public static DataBaseHandler<Member, Member> getInstance() {
        if(instance == null){
            synchronized (CustomerRepository.class) {
                if(instance == null){
                    instance = new CustomerRepository();
                }
            }
        }
        return instance;
    }


    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

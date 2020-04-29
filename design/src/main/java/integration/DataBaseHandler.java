package integration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;

public interface DataBaseHandler<obj,param> {
     static final String URL = "jdbc:h2:file:./userDB;DB_CLOSE_DELAY=-1";
     static final String INSERT_TEMPLATE = "INSERT INTO  %s VALUES ('%s', '%s' FORMAT JSON);";
     static final String SELECT_TEMPLATE = "SELECT * FROM %s WHERE id='%s';";
     static final String SELECT_ALL_TEMPLATE = "SELECT * FROM %s;";
     static final ObjectMapper objectMapper = new ObjectMapper();
     static final String UPDATE_TEMPLATE = "UPDATE %s SET stockstatus=%s WHERE id='%s';";

     boolean register(String id, param obj);
     boolean find(String id);
     obj collect(String id);

     static void printSQLException(SQLException ex){
          System.err.println("SQLState: " + ex.getSQLState());
          System.err.println("Error Code: " + ex.getErrorCode());
          System.err.println("Message: " + ex.getMessage());
          Throwable t = ex.getCause();
          System.out.println("Cause: " + t);
     }

}

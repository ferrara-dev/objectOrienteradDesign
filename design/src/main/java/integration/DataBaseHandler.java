package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import util.JsonUtil;

import java.sql.*;

public interface DataBaseHandler<obj,param> {

     String INSERT_TEMPLATE = "INSERT INTO  %s VALUES ('%s', '%s' FORMAT JSON);";
     String SELECT_TEMPLATE = "SELECT * FROM %s WHERE id='%s';";
     String SELECT_ALL_TEMPLATE = "SELECT * FROM %s;";
     ObjectMapper objectMapper = JsonUtil.getMAPPER();
     String UPDATE_TEMPLATE = "UPDATE %s SET vdata='%s' FORMAT JSON WHERE id='%s';";


     boolean register(String id, param obj);

     boolean find(String id);

     obj collect(String id);

     static void printSQLException(SQLException ex) {
          System.err.println("SQLState: " + ex.getSQLState());
          System.err.println("Error Code: " + ex.getErrorCode());
          System.err.println("Message: " + ex.getMessage());
          Throwable t = ex.getCause();
          System.out.println("Cause: " + t);
     }

     static void shutDown() {

     }
}
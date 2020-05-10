package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.banking.BankAccount;

public interface Bank extends DataBaseHandler {
    String URL = "jdbc:h2:file:./userDB;DB_CLOSE_DELAY=-1";
    String INSERT_TEMPLATE = "INSERT INTO %s VALUES ('%s', '%s' FORMAT JSON);";
    String SELECT_TEMPLATE = "SELECT * FROM %s WHERE id='%s';";
    String SELECT_ALL_TEMPLATE = "SELECT * FROM %s;";
    ObjectMapper objectMapper = new ObjectMapper();
    String UPDATE_TEMPLATE = "UPDATE %s SET vdata='%s' FORMAT JSON WHERE id='%s';";

}

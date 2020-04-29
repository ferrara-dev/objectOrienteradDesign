package startup.databasecreator;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.h2.tools.RunScript;
import startup.Main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DataBaseCreator {
    private static final String URL = "jdbc:h2:file:./userDB;DB_CLOSE_DELAY=-1";


    public DataBaseCreator() {

    }

    public boolean createTable() {
        InputStream inputStream = null;
        Reader targetReader = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(URL)) {
            inputStream = Main.class.getResourceAsStream("/db.sql");
            targetReader = new InputStreamReader(inputStream);
            // run script file with H2 RunScript
            RunScript.execute(connection, targetReader);
        } catch (JdbcSQLIntegrityConstraintViolationException ei) {
            System.out.println("Data already created!");
            return false;
        } catch (SQLException e) {
            printSQLException(e);
            System.exit(-1);
        } finally {
            try {
                if (Objects.nonNull(inputStream)) {
                    inputStream.close();
                }
                if (Objects.nonNull(targetReader)) {
                    targetReader.close();
                }
            } catch (Exception e) {
                System.exit(-1);
            }
        }
        return true;
    }

    public static void printSQLException(SQLException ex) {
        System.err.println("SQLState: " + ex.getSQLState());
        System.err.println("Error Code: " + ex.getErrorCode());
        System.err.println("Message: " + ex.getMessage());
        Throwable t = ex.getCause();
        System.out.println("Cause: " + t);
    }

}





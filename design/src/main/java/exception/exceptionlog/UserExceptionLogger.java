package exception.exceptionlog;
import util.Calendar;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UserExceptionLogger implements ExceptionLogger {
    private boolean fileIsAccesible;
    private String logFileUrl = "user-exception-log.txt";
    private PrintWriter logFile;
    private static UserExceptionLogger instance;

    private UserExceptionLogger() {
        try {
            logFile = new PrintWriter(new FileWriter(logFileUrl), true);
            fileIsAccesible = true;
        } catch (IOException ioe){
            fileIsAccesible = false;
            System.out.println(ioe.getMessage());
        }
    }

    public void setLogFile(PrintWriter logFile) {
        this.logFile = logFile;
    }

    /**
     *  Singleton method used to create an instance of the class
     *  and make sure that multiple instances can not be created
     *  <code> synchronized </code> keyword is used to make the
     *  calls to this method thread safe.
     * * @return
     */
    public static UserExceptionLogger getInstance(){
        if(instance == null){
            synchronized (UserExceptionLogger.class) {
                if(instance == null){
                    instance = new UserExceptionLogger();
                }
            }
        }
        return instance;
    }

    @Override
    public void logException(Exception exception) {
        if(fileIsAccesible) {
            StringBuilder logMsgBuilder = new StringBuilder();
            logMsgBuilder.append(Calendar.getTimeAndDate());
            logMsgBuilder.append(", Exception was thrown: ");
            logMsgBuilder.append(exception.getMessage());
            logFile.println(logMsgBuilder);
            exception.printStackTrace(logFile);
        }
    }

    @Override
    public void logException(String additionalMessage, Exception exception) {

    }
}

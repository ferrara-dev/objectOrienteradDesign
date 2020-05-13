package exception.exceptionlog;

import util.Calendar;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BusinessExceptionLogger implements ExceptionLogger {
    private boolean fileIsAccesible;
    private final String LOG_FILE_URL = "Business-Exception-Logger.txt";
    private PrintWriter logFile;
    private static BusinessExceptionLogger instance;

    private BusinessExceptionLogger() {
        try {
            logFile = new PrintWriter(new FileWriter(LOG_FILE_URL), true);
            fileIsAccesible = true;
        } catch (IOException ioe){
            fileIsAccesible = false;
            System.out.println(ioe.getMessage());
        }
    }

    /**
     *  Singleton method used to create an instance of the class
     *  and make sure that multiple instances can not be created
     *  <code> synchronized </code> keyword is used to make the
     *  calls to this method thread safe.
     * * @return
     */
    public static BusinessExceptionLogger getInstance(){
        if(instance == null){
            synchronized (BusinessExceptionLogger.class) {
                if(instance == null){
                    instance = new BusinessExceptionLogger();
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
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(Calendar.getTimeAndDate());
        logMsgBuilder.append(", Exception was thrown: " + additionalMessage);
        logMsgBuilder.append(exception.getMessage());
        logFile.println(logMsgBuilder);
        exception.printStackTrace(logFile);

    }
}

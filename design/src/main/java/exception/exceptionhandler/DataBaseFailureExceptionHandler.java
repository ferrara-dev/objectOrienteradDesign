package exception.exceptionhandler;

import integration.DataBaseHandler;
import observer.exceptionobserver.ExceptionEvent;
import exception.exceptionlog.ExceptionLogStrategy;
import util.AppProperties;
import exception.DataBaseAccessFailureException;
import exception.ErrorId;

import java.io.*;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Handles a business exception that is not displayed to the user
 */
public class DataBaseFailureExceptionHandler extends ExceptionHandlingChain {
    DataBaseFailureExceptionHandler(ExceptionHandlingChain successor) {
        super(successor);
        exceptionLogger = ExceptionLogStrategy.BUSINESS_EXCEPTION_LOG.get();
    }

    @Override
    public void handle(Exception exception) {
        if (exception instanceof DataBaseAccessFailureException) {
            DataBaseAccessFailureException dataBaseAccessFailureException = (DataBaseAccessFailureException) exception;
            handleDataBaseAccessFailure(dataBaseAccessFailureException);
        } else {
            if (Objects.nonNull(successor)) {
                successor.handle(exception);
            } else {
                exceptionLogger = ExceptionLogStrategy.SEVERE_EXCEPTION_LOG.get();
                exceptionLogger.logException(exception);
                exceptionListener.exceptionThrown(new ExceptionEvent(ErrorId.UNDEFINED_PROBLEM));
            }
        }
    }
    private void handleDataBaseAccessFailure(DataBaseAccessFailureException dataBaseAccessFailureException) {
        exceptionLogger.logException(dataBaseAccessFailureException);
        if (dataBaseAccessFailureException.getCause() instanceof SQLException) {
            SQLException cause = (SQLException) dataBaseAccessFailureException.getCause();
            boolean fileIsLocked = checkFileLock(cause);
            if(fileIsLocked) {
                createDataBaseBackUp();
                connectToBackUp();
                dataBaseAccessFailureException.setFixed(true);
                System.err.println(dataBaseAccessFailureException.getMessage() + "\n Exception Handled by copying the database \n");
                exceptionLogger.logException("Handled by copying the database \n", dataBaseAccessFailureException);
            }
        }

        ErrorId errorId = dataBaseAccessFailureException.getErrorId();
        super.notifyListener(new ExceptionEvent(dataBaseAccessFailureException, errorId));
    }

    private boolean checkFileLock(SQLException cause){
        Throwable nextCause = cause;
        do {
            if (nextCause instanceof java.lang.IllegalStateException)
                if (nextCause.getMessage().contains("The file is locked:")) {
                    return true;
                }
            nextCause = nextCause.getCause();
        } while (nextCause!=null);
        return false;
    }

    private boolean createDataBaseBackUp(){
        File sourceFile = new File("userDB.mv.db");
        File destinationFile = new File("userDB-BackUp.mv.db");
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        sourceFile.deleteOnExit();
        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(destinationFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        final FileChannel inChannel = inputStream.getChannel();
        final FileChannel outChannel = outputStream.getChannel();

        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try {
                inChannel.close();
                outChannel.close();
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                exceptionLogger.logException("While attempting to handle locked database file", e);
                return false;

            }
        }
        return true;
    }

    private boolean connectToBackUp(){
        DataBaseHandler.shutDown();
        AppProperties.setDataBaseURL("userDB-BackUp");
        System.out.println(AppProperties.getDataBaseURL());
        return false;
    }

}
package exception;

import integration.PhysicalObjectsRepository;
import org.junit.After;
import org.junit.Test;
import exception.exceptionhandler.ExceptionHandler;
import exception.exceptionhandler.ExceptionHandlingChain;
import util.AppProperties;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import static junit.framework.TestCase.assertTrue;

public class SystemStartUpFailureTest {
    private FileLock lock = null;
    @After
    public void after(){
        AppProperties.resetDataBaseURL();
        unlockDataBaseFile();
    }
    @Test(expected = SystemStartUpFailureException.class)
    public void testRegisterStartUpFailure(){
        AppProperties.resetDataBaseURL();
        lockDataBaseFile();
        PhysicalObjectsRepository.getInstance().startUpRegister(null);
    }

    @Test
    public void testRegisterStartUpFailure2(){
        AppProperties.resetDataBaseURL();
        lockDataBaseFile();
        try {
            PhysicalObjectsRepository.getInstance().startUpRegister(null);
        } catch (SystemStartUpFailureException e){
            int i = 0;
          if(e.getCause() instanceof DataBaseAccessFailureException)
                i++;
            assertTrue(i>0);
        }
    }

    /**
     * Exception handler should log the exception and
     * then exit the system with exit code -1
     */
    @Test
    public void testRegisterStartUpFailureHandling(){
        AppProperties.resetDataBaseURL();
        lockDataBaseFile();
        ExceptionHandler.createExceptionHandlingChain(null);
        ExceptionHandlingChain exceptionHandlingChain = ExceptionHandler.getExceptionHandlingChain();
        try {
            PhysicalObjectsRepository.getInstance().startUpRegister(null);
        } catch (SystemStartUpFailureException e){
            exceptionHandlingChain.handle(e);
        }
    }

    private void lockDataBaseFile() {
        try {
            FileInputStream fis = new FileInputStream("userDB.mv.db");
            FileChannel channel = fis.getChannel();
            lock = channel.lock(0, Long.MAX_VALUE, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unlockDataBaseFile() {
        try {
            if (lock != null)
                lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

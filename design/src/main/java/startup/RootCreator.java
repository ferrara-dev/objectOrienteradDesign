package startup;
import integration.PhysicalObjectsRepository;
import exception.exceptionhandler.ExceptionHandler;
import exception.exceptionlog.ExceptionLogStrategy;
import startup.layer.ControllerCreator;
import startup.layer.ServiceCreator;
import startup.layer.ViewCreator;
import exception.SystemStartUpFailureException;

import java.io.IOException;
import java.net.URISyntaxException;


public class RootCreator {
    private ControllerCreator controllerCreator;
    private ServiceCreator serviceCreator;
    private ViewCreator viewCreator;

    public void initServiceLayer(){
        serviceCreator = new ServiceCreator();
        serviceCreator.setUpObservers(viewCreator.collectObservers());
    }

    public void initControllerLayer(){
        controllerCreator = new ControllerCreator(serviceCreator);
        controllerCreator.configureObservers(viewCreator.collectInputViews());
    }

    public void initExceptionHandler(){
        ExceptionHandler.createExceptionHandlingChain(viewCreator.getExceptionView());
    }

    public void initPeripherals(){
        try {
             PhysicalObjectsRepository.getInstance().startUpRegister(viewCreator.collectObservers());
        }
        catch (SystemStartUpFailureException systemStartUpFailureException){
            ExceptionLogStrategy.SEVERE_EXCEPTION_LOG.get().logException(systemStartUpFailureException);
        }
    }

    public void createGui() {
        viewCreator = new ViewCreator();
        viewCreator.createView();
    }

    public void showGUI() throws IOException, URISyntaxException{
        viewCreator.showView();
    }

    /**
     * The following getter methods are only used for testing
     *
     *
     *
     *
     */
    public ControllerCreator getControllerCreator() {
        return controllerCreator;
    }

    public ServiceCreator getServiceCreator() {
        return serviceCreator;
    }

    public ViewCreator getViewCreator() {
        return viewCreator;
    }

}

package startup;
import integration.PhysicalObjectsRepository;
import service.handlerpattern.exceptionlog.ExceptionLogStrategy;
import startup.layer.ControllerCreator;
import startup.layer.ServiceCreator;
import startup.layer.ViewCreator;
import util.exception.SystemStartUpFailureException;

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

    public void initPeripherals(){
        try {
             PhysicalObjectsRepository.getInstance().startUpRegister(viewCreator.collectObservers());
        }
        catch (SystemStartUpFailureException systemStartUpFailureException){
            ExceptionLogStrategy.STARTUP_EXCEPTION_LOG.get().logException(systemStartUpFailureException);
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

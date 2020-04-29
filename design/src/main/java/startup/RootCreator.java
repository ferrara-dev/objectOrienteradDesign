package startup;
import service.PhysicalObjectsRepository;
import startup.layercreator.ControllerCreator;
import startup.layercreator.ServiceCreator;
import startup.layercreator.ViewCreator;

import java.io.IOException;
import java.net.URISyntaxException;


public class RootCreator {
    private PhysicalObjectsRepository physicalObjectsRepository;
    private ControllerCreator controllerCreator;
    private ServiceCreator serviceCreator;


    ViewCreator viewCreator;

    public RootCreator(){
        physicalObjectsRepository = new PhysicalObjectsRepository();
    }

    public void initServiceLayer(){
        serviceCreator = new ServiceCreator(physicalObjectsRepository);
    }


    public void initControllerLayer(){
        controllerCreator = new ControllerCreator(serviceCreator);
    }

    public void createGui() {
        viewCreator = new ViewCreator(controllerCreator);
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

    public PhysicalObjectsRepository getPhysicalObjectsRepository() {
        return physicalObjectsRepository;
    }

    public ViewCreator getViewCreator() {
        return viewCreator;
    }

}

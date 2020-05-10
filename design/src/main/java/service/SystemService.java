package service;

import factory.IntegrationFactory;
import integration.PhysicalObjectsRepository;
import model.physicalobjects.Register;
import model.sale.saleinformation.salestate.State;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
import observer.StateChange;
import util.exception.SystemStartUpFailureException;

import java.util.ArrayList;

public class SystemService {
    private ArrayList<EventObserver> eventObservers;

    public void initRegisterStartup() {
        Register register = null;
        try {
             register = (Register) IntegrationFactory.REGISTER_BALANCE_ACCOUNT.getDataBaseHandler().collect("RegisterOne");
        } catch (Exception e){
            throw new SystemStartUpFailureException(e);
        }
        register.setObservers(eventObservers);
        register.notifyObservers(new PropertyChangeEvent("registerBalance",register.getBalance(),register.getBalance()));
        register.notifyObservers(new StateChange(State.REGISTER_ACTIVE_STATE));
        PhysicalObjectsRepository.getInstance().setCashRegister(register);
    }

    private void startRegister(Register register){
        register.setObservers(eventObservers);
    }

    public void setEventObservers(ArrayList<EventObserver> eventObservers) {
        this.eventObservers = eventObservers;
    }

    private void notifyObservers(ObservedEvent observedEvent){

    }
}

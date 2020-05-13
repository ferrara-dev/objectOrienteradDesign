package observer.modelobserver;

import model.sale.saleinformation.salestate.State;
import observer.modelobserver.ObservedEvent;

public class StateChange implements ObservedEvent {
    State state;
    String propertyName;
    public StateChange(State state){
        this.state = state;
        propertyName = state.toString();
    }

    @Override
    public State getEventSource() {
        return state;
    }

    @Override
    public Object getPropertyValue() {
        return state;
    }

    @Override
    public String getPropertyName() {
        return state.toString();
    }

}

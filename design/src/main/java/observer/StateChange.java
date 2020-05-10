package observer;

import model.sale.saleinformation.salestate.State;

public class StateChange extends ObservedEvent {
    State state;

    public StateChange(State state){
        this.state = state;
    }

    @Override
    public State getEventSource() {
        return state;
    }

}

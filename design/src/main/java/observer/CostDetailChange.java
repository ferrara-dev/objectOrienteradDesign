package observer;

import observer.modelobserver.ObservedEvent;
import util.datatransferobject.CostDTO;

public class CostDetailChange implements ObservedEvent {

    @Override
    public CostDTO getEventSource() {
        return null;
    }

    @Override
    public Object getPropertyValue() {
        return null;
    }

    @Override
    public String getPropertyName() {
        return null;
    }
}

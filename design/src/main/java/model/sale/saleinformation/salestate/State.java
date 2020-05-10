package model.sale.saleinformation.salestate;

import java.util.ArrayList;

public enum State {
    REGISTER_ACTIVE_STATE,
    SALE_STARTUP_STATE,
    SALE_ACTIVE_STATE,
    SALE_PAYMENT_STATE,
    SALE_COMPLETE_STATE,
    SALE_STATES{

    };

    public ArrayList<State> getSaleStates(){
        ArrayList<State> stateArrayList = new ArrayList<>();
        stateArrayList.add(SALE_STARTUP_STATE);
        stateArrayList.add(SALE_ACTIVE_STATE);
        stateArrayList.add(SALE_PAYMENT_STATE);
        stateArrayList.add(SALE_COMPLETE_STATE);
        return stateArrayList;
    }
}

package factory.builderpattern;


import model.sale.SaleId;
import model.sale.saleinformation.cost.CostDetail;
import model.sale.saleinformation.ProductCart;
import model.sale.saleinformation.SaleSpecification;
import model.sale.saleinformation.salestate.SaleState;
import model.sale.saleinformation.salestate.State;
import observer.EventObserver;
import util.Calendar;
import java.util.ArrayList;
import java.util.Objects;

public enum SaleSpecificationFactory {
    DEFAULT_SALE {
        @Override
        public SaleSpecification create(ArrayList<EventObserver> observers) {
            {
                    CostDetail costDetail = new CostDetail();
                    costDetail.createDefault();
                    SaleSpecification sale = new SaleSpecBuilder()
                            .setDateOfSale(Calendar.getDate())
                            .setSaleId(new SaleId())
                            .setCart(new ProductCart())
                            .setCost(costDetail)
                            .build();

                ArrayList<State> saleStates = model.sale.saleinformation.salestate.State.SALE_STATES.getSaleStates();
                SaleState saleState = new SaleState();
                saleState.init(saleStates);
                sale.setSaleState(saleState);
                    if(Objects.nonNull(observers)) {
                        for(EventObserver eventObserver: observers) {
                            sale.getSaleState().addObserver(eventObserver);
                            sale.getCart().addObserver(eventObserver);
                            sale.getCost().addObserver(eventObserver);
                        }
                    }

                    return sale;
            }
        }

    };

    public abstract SaleSpecification create(ArrayList<EventObserver> observers);
}

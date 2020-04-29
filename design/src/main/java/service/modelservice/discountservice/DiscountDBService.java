package service.modelservice.discountservice;


import util.datatransferobject.DiscountDTO;
import factory.DiscountStrategyFactory;
import integration.DataBaseHandler;
import model.discount.Discount;
import model.discount.discountrule.DiscountRule;
import service.IntegrationService;
import service.modelservice.discountservice.discountstrategy.DiscountStrategy;
import util.Calendar;

import java.util.ArrayList;
import java.util.List;


public class DiscountDBService implements IntegrationService<ArrayList<DiscountRule>> {
    public static final Long HASH_KEY_ID = 123L;
    private final DiscountStrategyFactory factory = new DiscountStrategyFactory();
    DataBaseHandler<ArrayList, Discount> dataBaseHandler;

    public DiscountDBService(DataBaseHandler dataBaseHandler) {
        this.dataBaseHandler = dataBaseHandler;
    }


    @Override
    public void updateDB(Object object) {

    }

    @Override
    public ArrayList<DiscountRule> getFromDB(Object customerRequest) {
        List<DiscountDTO> discountDTOs = dataBaseHandler.collect(Calendar.getDayOfTheWeek());
        DiscountDTO[] dtos = discountDTOs.toArray(new DiscountDTO[0]);
        ArrayList<DiscountStrategy> collectedStrategies = new ArrayList<>();

        for(int i = 0; i < discountDTOs.size(); i++){
            collectedStrategies.add(factory.create(discountDTOs.get(i)));
        }
        ArrayList<DiscountRule> rules = new ArrayList<>();
        for(DiscountStrategy strategy: collectedStrategies)
            for(int i = 0; i < strategy.getRules().size(); i++)
                rules.add(strategy.getRules().get(i));

        return  rules;
    }

    @Override
    public boolean find(Object customer) {
        return false;
    }

}
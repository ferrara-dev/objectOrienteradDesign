package factory.discountstrategyFactory;

import factory.Factory;
import integration.DataBaseHandler;
import integration.customerdb.CustomerRepository;
import integration.discountdb.DiscountRepository;
import model.discount.Discount;
import model.discount.discountrule.DiscountRule;
import util.datatransferobject.DiscountDTO;
import service.modelservice.discountservice.discountstrategy.BulkDiscountStrategy;
import service.modelservice.discountservice.discountstrategy.DiscountStrategy;
import service.modelservice.discountservice.discountstrategy.TotalCostDiscountStrategy;

import java.util.ArrayList;

public class DiscountRuleFactory implements Factory<ArrayList<DiscountRule>, DiscountDTO> {
    private static DiscountRuleFactory instance;

    private DiscountRuleFactory(){
    }

    /**
     *  Singleton method used to create an instance of the class
     *  and make sure that multiple instances can not be created
     *  <code> synchronized </code> keyword is used to make
     *  calls to this method thread safe.
     * @return an implementation of <code> Factory </code> interface
     */
    public static Factory<ArrayList<DiscountRule>, DiscountDTO> getInstance() {
        if(instance == null){
            synchronized (DiscountRuleFactory.class) {
                if(instance == null){
                    instance = new DiscountRuleFactory();
                }
            }
        }
        return instance;
    }

    /**
     *  A Factory that creates instances of the algorithm used
     *  to match information about discount to a specific instance
     *  of a strategy.
     * All such instances must implement <code>DiscountStrategy</code>
     */
    @Override
    public ArrayList<DiscountRule> create(DiscountDTO discountDTO) {
        ArrayList<DiscountRule>  rules = new ArrayList<>();
            if(discountDTO.getType().equals(DiscountStrategy.BULK_DISCOUNT)){
                rules.addAll(new BulkDiscountStrategy().applyStrategy(discountDTO));

            }
            else if(discountDTO.getType().equals(DiscountStrategy.TOTAL_PRICE_DISCOUNT)){
                rules.addAll(new TotalCostDiscountStrategy().applyStrategy(discountDTO));
            }
            else
                throw new IllegalArgumentException();

            return rules;
    }

}
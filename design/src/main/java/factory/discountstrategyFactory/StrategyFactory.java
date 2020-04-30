package factory.discountstrategyFactory;

import factory.Factory;
import service.modelservice.discountservice.discountstrategy.DiscountStrategy;

public interface StrategyFactory  {
    DiscountStrategy getStrategy();
}

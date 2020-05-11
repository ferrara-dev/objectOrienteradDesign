package factory.discountstrategyFactory;

import service.discountservice.discountstrategy.DiscountStrategy;

public interface StrategyFactory  {
    DiscountStrategy getStrategy();
}

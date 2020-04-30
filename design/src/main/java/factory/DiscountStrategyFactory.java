package factory;

import util.datatransferobject.DiscountDTO;
import service.modelservice.discountservice.discountstrategy.BulkDiscountStrategy;
import service.modelservice.discountservice.discountstrategy.DiscountStrategy;
import service.modelservice.discountservice.discountstrategy.TotalCostDiscountStrategy;

public class DiscountStrategyFactory implements Factory<DiscountStrategy, DiscountDTO> {

    /**
     *  A Factory that creates instances of the algorithm used
     *  to match information about discount to a specific instance
     *  of a strategy.
     * All such instances must implement <code>DiscountStrategy</code>
     */
    @Override
    public DiscountStrategy create(DiscountDTO discountDTO) {
        DiscountStrategy strategy;
            if(discountDTO.getType().equals(DiscountStrategy.BULK_DISCOUNT)){
                strategy = new BulkDiscountStrategy(discountDTO);
            }
            else if(discountDTO.getType().equals(DiscountStrategy.TOTAL_PRICE_DISCOUNT)){
               strategy = new TotalCostDiscountStrategy(discountDTO);
            }
            else
                throw new IllegalArgumentException();

            return strategy;
    }

}
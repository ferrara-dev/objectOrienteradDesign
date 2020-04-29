package factory;

import util.datatransferobject.DiscountDTO;
import service.modelservice.discountservice.discountstrategy.BulkDiscountStrategy;
import service.modelservice.discountservice.discountstrategy.DiscountStrategy;
import service.modelservice.discountservice.discountstrategy.TotalCostDiscountStrategy;

public class DiscountStrategyFactory implements Factory<DiscountStrategy, DiscountDTO> {

    /**
     *  A Factory that creates instances of the algorithm used
     *  for matching available discounts.
     * All such instances must implement <code>DiscountStrategy</code>
     */
    @Override
    public DiscountStrategy create(DiscountDTO discountDTO) {
        DiscountStrategy strategy = null;
            if(discountDTO.getType().equals(DiscountStrategy.BULK_DISCOUNT)){
                strategy = getBulkDiscount(discountDTO);
            }
            else if(discountDTO.getType().equals(DiscountStrategy.PRICE_DISCOUNT)){
               strategy = getPriceDiscount(discountDTO);
            }
            return strategy;
    }

    private DiscountStrategy getBulkDiscount(DiscountDTO discountDTO) {
        BulkDiscountStrategy discountStrategy = new BulkDiscountStrategy();
        discountStrategy.applyStrategy(discountDTO);
        return discountStrategy;
    }


    private DiscountStrategy getPriceDiscount(DiscountDTO discountDTO) {
        TotalCostDiscountStrategy discountStrategy = new TotalCostDiscountStrategy();
        discountStrategy.applyStrategy(discountDTO);
        return discountStrategy;
    }

}

/**
 * 13 * Returns a <code>Matcher</code> performing the default
 * 14 * matching algorithm. The class name of the default
 * 15 * <code>Matcher</code> implementation is read from the
 * 16 * system property
 * 17 * <code>se.leiflindback.rentcar.matcher.classname</code>
 * 18 *
 * 19 * @return The default matcher
 * 20 * @throws ClassNotFoundException If unable to load the
 * 21 * default matcher class.
 * public DiscountStrategyFactory() {
 * <p>
 * }
 * <p>
 * public List<DiscountStrategy> getDiscountStrategies(DiscountService discountService) {
 * List<DiscountStrategy> discountStrategies = new ArrayList<>();
 * DiscountStrategy strategy;
 * for (DiscountDTO dto : discountService.getDiscountDTOS()) {
 * if (dto.getType() == DiscountStrategy.BULK_DISCOUNT) {
 * strategy = new BulkDiscountStrategy(discountService.getSaleService());
 * strategy.applyStrategy(dto);
 * strategy.calculateDiscount();
 * discountStrategies.add(strategy);
 * } else if (dto.getType() == DiscountStrategy.PRICE_DISCOUNT) {
 * strategy = new PriceDiscountStrategy(discountService.getSaleService());
 * strategy.applyStrategy(dto);
 * strategy.applyDiscount();
 * discountStrategies.add(strategy);
 * }
 * }
 * <p>
 * return discountStrategies;
 * }
 *
 * @Override public List<DiscountStrategy> create(DiscountDTO... discountDTOS) {
 * return null;
 * }
 * }
*/
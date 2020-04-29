package Integration;

import util.datatransferobject.DiscountDTO;
import factory.DiscountStrategyFactory;
import factory.Factory;
import integration.DataBaseHandler;
import integration.discountdb.DiscountRegistryHandler;
import model.discount.Discount;
import org.junit.Test;
import service.modelservice.discountservice.discountstrategy.DiscountStrategy;
import util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class DiscountDBTest {


    @Test
    public void testDiscountRegistryHandler(){
        /* 1 */
        DataBaseHandler<List, Discount> dataBaseHandler = new DiscountRegistryHandler();
        Factory<DiscountStrategy, DiscountDTO> strategyFactory = new DiscountStrategyFactory();
        List<DiscountDTO> dtos = dataBaseHandler.collect(Calendar.getDayOfTheWeek());
        assertEquals(dtos.get(0).getClass(),DiscountDTO.class);
        DiscountStrategy strategies = strategyFactory.create(dtos.get(0));
        assertNotNull(strategies);
    }
}

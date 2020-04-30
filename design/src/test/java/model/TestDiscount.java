package model;

import factory.DiscountStrategyFactory;
import factory.Factory;
import integration.customerdb.CustomerDBHandler;
import integration.productdb.InventoryHandler;
import integration.productdb.Product;
import model.customer.Member;
import model.discount.discountrule.itemdiscountrule.ItemDiscountRule;
import model.discount.discountrule.pricediscountrule.TotalCostDiscountRule;
import model.discount.discounttypes.itemdiscount.BulkDiscount;
import model.discount.discounttypes.itemdiscount.ItemDiscount;
import model.sale.Sale;
import model.sale.SaleItem;
import org.junit.Before;
import org.junit.Test;
import service.modelservice.discountservice.discountstrategy.DiscountStrategy;
import util.datatransferobject.DiscountDTO;

import static org.junit.Assert.*;


public class TestDiscount {
    Factory<DiscountStrategy,DiscountDTO> discountStrategyFactory;
    DiscountDTO priceDiscountDTO;
    DiscountDTO itemDiscountDTO;
    Member member;
    @Before
    public void startup() {
        discountStrategyFactory = new DiscountStrategyFactory();
        priceDiscountDTO = new DiscountDTO("Price Discount", "1000", "0.20", "-1", "0");
        itemDiscountDTO = new DiscountDTO("Bulk Discount", "10", "0.20", "-1", "2");

       member = new CustomerDBHandler().collect("940412-1395");
    }

    @Test
    public void testDiscountStrategy() {
            DiscountStrategy priceDiscountStrategy = discountStrategyFactory.create(priceDiscountDTO);
            assertNotNull(priceDiscountStrategy);
            TotalCostDiscountRule [] priceDiscountRules = (TotalCostDiscountRule[]) priceDiscountStrategy.getRules();

            int size = priceDiscountRules.length;
            assertEquals(1,size);
            boolean isEnough = priceDiscountRules[0].checkRequierments(1000);
            assertTrue(isEnough);

            isEnough = priceDiscountRules[0].checkRequierments(20);
            assertFalse(isEnough);

            DiscountStrategy itemDiscountStrategy = discountStrategyFactory.create(itemDiscountDTO);
            ItemDiscountRule [] itemDiscountRules = (ItemDiscountRule[]) itemDiscountStrategy.getRules();
            int size1 = itemDiscountRules.length;
            assertNotNull(itemDiscountRules[0]);

            int itemId = itemDiscountRules[0].getItemId();
            int minimumAmount = itemDiscountRules[0].getMinimumAmountOfItems();

            assertNotEquals(0,itemId);


            Product product = new InventoryHandler().collect(String.valueOf(itemId));
            assertNotNull(product);

            Sale sale = new Sale();
            sale.startSale();
            sale.createDefault();
            sale.getCart().addProduct(new SaleItem(product,minimumAmount));
            sale.updateCost();
            double toPay = sale.getCost().getNumber();
            assertNotEquals(0,toPay);


            ItemDiscount itemDiscount = new BulkDiscount(itemDiscountRules[0]);
            assertNotNull(itemDiscount);
            double productPrice = product.getTotalPrice();
            double totalPriceReduction =  itemDiscount.getRequirement()*productPrice*itemDiscount.getDiscountRule().getDiscountRate();
            itemDiscount.setTotalPriceReduction(totalPriceReduction);
            sale.getCart().addItemDiscount(itemId,itemDiscount);
            sale.updateCost();
            double updatedToPay = sale.getCost().getNumber();
        System.out.println((toPay));
        System.out.println(updatedToPay);
            assertNotEquals(updatedToPay,toPay);
            //sale.getCart().addItemDiscount(itemId,);
    }
}

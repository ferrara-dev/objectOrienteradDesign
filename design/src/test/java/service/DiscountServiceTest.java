package service;

import model.customer.MemberDiscountRequest;
import service.modelservice.saleservice.SaleService;
import startup.layer.ServiceCreator;
import org.junit.Before;
import org.junit.Test;
import service.modelservice.discountservice.DiscountService;
import util.exception.NotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DiscountServiceTest {
    ServiceCreator serviceFactory;
    DiscountService discountService;

    @Before
    public void setup() {
        serviceFactory = new ServiceCreator(new PhysicalObjectsRepository());
        discountService = serviceFactory.getDiscountService();
    }


    @Test(expected = NotFoundException.class)
    public void testExceptionThrownRequestCustomerDiscount() {
        discountService.initiateDiscountRequest("950412-1395");
        discountService.applyValidDiscounts();
    }

    /**
     * 1. Test if <code> CustomerDiscountRequest </code> is initiated and
     *    that the right customer is copied to the request
     *
     * 2. Test if the <code> currentSale </code> attribute of <code> discountRequest </code>
     *    is set as intended
     */
    @Test
    public void testRequestCustomerDiscount() {
        /* 1 */
        SaleService saleService = serviceFactory.getSaleService();
        saleService.startSale();
        saleService.initSaleDefault();
        saleService.registerProduct(2,10);
        discountService.initiateDiscountRequest("940412-1395");

        MemberDiscountRequest discountRequest =  discountService.getMemberDiscountRequest();
        assertNotNull(discountRequest);
        assertEquals(discountRequest.getMember().getCustomerId().getPersonalNumber(),"940412-1395");
    }

}

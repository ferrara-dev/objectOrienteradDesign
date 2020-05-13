package service;

import exception.ErrorId;
import exception.exceptionhandler.ExceptionHandler;
import exception.exceptionhandler.ExceptionHandlingChain;
import exception.notfoundexception.NotFoundException;
import factory.IntegrationFactory;
import model.sale.saleinformation.SaleTransaction;
import org.junit.Before;
import org.junit.Test;
import service.discountservice.DiscountService;
import startup.layer.ServiceCreator;
import util.datatransferobject.DiscountDTO;
import util.datatransferobject.SaleItemDTO;
import view.exceptionview.ExceptionView;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DiscountTest {
    ExceptionView exceptionView;
    ExceptionHandlingChain exceptionHandlingChain;
    DiscountService discountService;
    ServiceCreator serviceCreator;
    ServiceFacade serviceFacade;
    @Before
    public void startUp() {
        serviceCreator = new ServiceCreator();
        discountService = serviceCreator.getDiscountService();
        exceptionView = new ExceptionView();
        ExceptionHandler.createExceptionHandlingChain(exceptionView);
        exceptionHandlingChain = ExceptionHandler.getExceptionHandlingChain();
    }

    @Test
    public void testDiscountRequest1(){
        SaleTransaction saleTransaction = setUpSaleMock();
        double totalSalePrice = saleTransaction.getCost().getRunningTotal().getNumber().doubleValue();
        discountService.processDiscountRequest("940412-1395",saleTransaction);
        double discountedTotalSalePrice = saleTransaction.getCost().getRunningTotal().getNumber().doubleValue();
        System.out.println(totalSalePrice);
        System.out.println(discountedTotalSalePrice);
        assertNotEquals(totalSalePrice,discountedTotalSalePrice);
    }

    @Test(expected = NotFoundException.class)
    public void testDiscountRequest2(){
        SaleTransaction saleTransaction = setUpSaleMock();
        double totalSalePrice = saleTransaction.getCost().getRunningTotal().getNumber().doubleValue();
        discountService.processDiscountRequest("ABC",saleTransaction);
    }

    @Test
    public void testDiscountRequest3(){
        SaleTransaction saleTransaction = setUpSaleMock();
        double totalSalePrice = saleTransaction.getCost().getRunningTotal().getNumber().doubleValue();
        try {
            discountService.processDiscountRequest("ABC", saleTransaction);
        } catch (NotFoundException e){
            exceptionHandlingChain.handle(e);
            assertEquals(e.getErrorId().code,ErrorId.CUSTOMER_ID_NOT_FOUND.code);
        }
    }

    private SaleTransaction setUpSaleMock(){
        ServiceFacade serviceFacade = new ServiceFacade(serviceCreator);
        serviceFacade.startSale();
        serviceFacade.registerProduct(new SaleItemDTO(1,40));
        SaleTransaction saleTransaction = serviceFacade.getSaleService().getSaleInformation();
        return saleTransaction;
    }

    /**
     *
     */
    @Test
    public void testGetDiscountOfTheDay() {
        ArrayList<DiscountDTO>  discountDTOS = (ArrayList<DiscountDTO>) IntegrationFactory.DISCOUNT_REPO.getDataBaseHandler().collect("MONDAY");
        assertNotNull(discountDTOS);
    }

    /**
     * Test if the error code is correct
     */
    @Test
    public void testInvalidProductId2() {
        try {
            IntegrationFactory.DISCOUNT_REPO.getDataBaseHandler().collect("111111");
        } catch (NotFoundException e) {
            assertEquals(ErrorId.DAILY_DISCOUNT_NOT_FOUND.code, e.getErrorId().code);
        }
    }

    /**
     * Test that the message is correct
     */
    @Test
    public void testInvalidProductId3() {
        try {
            IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("111111");
        } catch (NotFoundException e) {
            assertEquals("id \"111111\"", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Test that the handling chain works
     */
    @Test
    public void testInvalidProductId4() {
        try {
            IntegrationFactory.PRODUCT_REPO.getDataBaseHandler().collect("111111");
        } catch (NotFoundException e) {
            exceptionHandlingChain.handle(e);
            e.printStackTrace();
        }
    }

}

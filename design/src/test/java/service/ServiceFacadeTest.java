package service;

import model.sale.SaleItem;
import model.sale.saleinformation.salestate.SaleState;
import model.sale.saleinformation.salestate.State;
import model.transaction.saleTransaction.SaleTransaction;
import org.junit.Before;
import org.junit.Test;
import startup.layer.ServiceCreator;
import util.datatransferobject.PaymentDTO;
import util.datatransferobject.SaleItemDTO;
import util.sequence.SequenceIterator;
import java.math.BigDecimal;
import static junit.framework.TestCase.*;

public class ServiceFacadeTest {
    ServiceFacade serviceFacade;

    @Before
    public void startUp(){
        serviceFacade = new ServiceFacade(new ServiceCreator());
        serviceFacade.startSale();
    }

    /**
     * Test if running total is updated
     */
    @Test
    public void testRegisterProduct1(){
        SaleTransaction saleTransaction = serviceFacade.getSaleService().getSaleInformation();
        double firstRunningTotal  = saleTransaction.getCost().getRunningTotal().getNumber().doubleValue();
        System.out.println(firstRunningTotal);
        SaleItemDTO firstItem = new SaleItemDTO(3,1);
        serviceFacade.registerProduct(firstItem);
        double secondRunningTotal = saleTransaction.getCost().getRunningTotal().getNumber().doubleValue();
        System.out.println(secondRunningTotal);
        assertNotSame(firstRunningTotal,secondRunningTotal);
    }

    /**
     * Test if totalVat is updated
     */
    @Test
    public void testRegisterProduct2(){
        SaleTransaction saleTransaction = serviceFacade.getSaleService().getSaleInformation();
        double firstTotalVAT  = saleTransaction.getCost().getTotalVAT().getNumber().doubleValue();
        SaleItemDTO firstItem = new SaleItemDTO(3,1);
        serviceFacade.registerProduct(firstItem);
        double secondTotalVAT = saleTransaction.getCost().getTotalVAT().getNumber().doubleValue();
        assertNotSame(firstTotalVAT,secondTotalVAT);
    }

    /**
     * Test if quantity is updated
     */
    @Test
    public void testRegisterProduct3(){
        SaleTransaction saleTransaction = serviceFacade.getSaleService().getSaleInformation();
        SaleItemDTO firstItem = new SaleItemDTO(3,1);
        SaleItemDTO secondItem = new SaleItemDTO(3,1);
        serviceFacade.registerProduct(firstItem);
        double firstTotalVAT  = saleTransaction.getCost().getTotalVAT().getNumber().doubleValue();
        System.out.println(firstTotalVAT);
        serviceFacade.registerProduct(secondItem);
        double secondTotalVAT = saleTransaction.getCost().getTotalVAT().getNumber().doubleValue();
        System.out.println(secondTotalVAT);
        int quantity = firstItem.getQuantity();

        SequenceIterator<SaleItem> iterator = saleTransaction.getCart().getSequenceIterator();
        while(!iterator.isOver()){
            if(iterator.getCurrentItem().getProduct().getItemId() == 3){
                quantity = iterator.getCurrentItem().getQuantity();
                break;
            }
            iterator.nextItem();
        }

        assertEquals(2,quantity);
    }

    /**
     * Test if the correct state is set
     */
    @Test
    public void testEndSale1(){
        SaleTransaction saleTransaction = serviceFacade.getSaleService().getSaleInformation();
        double firstTotalVAT  = saleTransaction.getCost().getTotalVAT().getNumber().doubleValue();
        SaleItemDTO firstItem = new SaleItemDTO(3,1);
        serviceFacade.registerProduct(firstItem);
        double secondTotalVAT = saleTransaction.getCost().getTotalVAT().getNumber().doubleValue();

        serviceFacade.endSale();
        SaleState saleState = saleTransaction.getSaleState();
        State state = saleState.getCurrentState();
        assertSame(State.SALE_PAYMENT_STATE,state);
    }

    /**
     * Test if the final cost is set to equal the running total
     */
    @Test
    public void testEndSale2(){
        SaleTransaction saleTransaction = serviceFacade.getSaleService().getSaleInformation();
        double firstTotalVAT  = saleTransaction.getCost().getTotalVAT().getNumber().doubleValue();
        SaleItemDTO firstItem = new SaleItemDTO(3,1);
        serviceFacade.registerProduct(firstItem);
        double secondTotalVAT = saleTransaction.getCost().getTotalVAT().getNumber().doubleValue();

        serviceFacade.endSale();
        BigDecimal finalCost = saleTransaction.getCost().getFinalCost().getNumber();
        System.out.println(finalCost.toString());
        BigDecimal runningTotal = saleTransaction.getCost().getRunningTotal().getNumber();
        System.out.println(runningTotal.toString());
        assertEquals(finalCost.doubleValue(),runningTotal.doubleValue());
    }


    /**
     * Test if the final cost is set to equal the running total
     */
    @Test
    public void testPaySale(){
        SaleTransaction saleTransaction = serviceFacade.getSaleService().getSaleInformation();
        double firstTotalVAT  = saleTransaction.getCost().getTotalVAT().getNumber().doubleValue();
        SaleItemDTO firstItem = new SaleItemDTO(3,1);

        serviceFacade.registerProduct(firstItem);
        double secondTotalVAT = saleTransaction.getCost().getTotalVAT().getNumber().doubleValue();

        serviceFacade.endSale();
        BigDecimal finalCost = saleTransaction.getCost().getFinalCost().getNumber();
        System.out.println(finalCost.toString());
        BigDecimal runningTotal = saleTransaction.getCost().getRunningTotal().getNumber();
        System.out.println(runningTotal.toString());

        serviceFacade.initPayment(new PaymentDTO(35,"Cash Payment"));

        double updatedRunningTotal = saleTransaction.getCost().getRunningTotal().getNumber().doubleValue();

        assertEquals(10, updatedRunningTotal,0);
    }

    @Test
    public void testDiscountRequest(){
        SaleTransaction saleTransaction = serviceFacade.getSaleService().getSaleInformation();
        double firstTotalVAT  = saleTransaction.getCost().getTotalVAT().getNumber().doubleValue();
        SaleItemDTO firstItem = new SaleItemDTO(4,20);
        serviceFacade.registerProduct(firstItem);
        double firstRunningTOtal = saleTransaction.getCost().getRunningTotal().getNumber().doubleValue();
        System.out.println(firstRunningTOtal);
        serviceFacade.requestDiscount("940412-1395");
        double updatedRunningTotal = saleTransaction.getCost().getRunningTotal().getNumber().doubleValue();
        System.out.println(updatedRunningTotal);
    }

}

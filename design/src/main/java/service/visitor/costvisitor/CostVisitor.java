package service.visitor.costvisitor;

import model.amount.RunningTotal;
import model.amount.TotalVAT;
import model.sale.SaleItem;
import model.sale.saleinformation.cost.CostDetail;

import observer.modelobserver.EventObserver;
import observer.modelobserver.PropertyChangeEvent;
import service.visitor.Visitor;
import util.datatransferobject.CostDTO;
import util.sequence.SequenceIterator;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Implementation of the <code> Visitor </code> interface that is used to visit the
 * <code> CostDetail </code> model and update its fields.
  */
public class CostVisitor implements Visitor<CostDetail,SequenceIterator<SaleItem>> {
    private SequenceIterator<SaleItem> iterator;
    private static CostVisitor instance;

    private CostVisitor() {

    }

    /**
     * Singleton method used to create an instance of the class
     * and make sure that multiple instances can not be created
     * <code> synchronized </code> keyword is used to make the
     * calls to the method thread safe.
     * * @return
     */
    public static Visitor<CostDetail,SequenceIterator<SaleItem>>  getInstance() {
        if (instance == null) {
            synchronized (CostVisitor.class) {
                if (instance == null) {
                    instance = new CostVisitor();
                }
            }
        }
        return instance;
    }

    @Override
    public void setData(SequenceIterator<SaleItem> itemSequenceIterator) {
        iterator = itemSequenceIterator;
    }

    /**
     * Override to update the sales <code> RunningTotal </code> and <code> TotalVAT </code>
     * @param costDetail
     */
    @Override
    public void processElement(CostDetail costDetail) {
        iterator.firstItem();
        updateSaleItemCost(iterator);
        updateSaleCost(costDetail, iterator);
        costHasChanged(costDetail);
    }

    private void costHasChanged(CostDetail costDetail){
        double runningTotal = costDetail.getRunningTotal().getNumber().doubleValue();
        double totalVAT = costDetail.getTotalVAT().getNumber().doubleValue();
        double priceDiscount = costDetail.getPriceDiscount().getTotalPriceReduction().doubleValue();
        double totalPriceOfDiscount = costDetail.getTotalDiscountOfPrice().doubleValue();
        double finalCost = costDetail.getFinalCost().getNumber().doubleValue();
        costDetail.notifyObservers(new PropertyChangeEvent("costDetail", new CostDTO(finalCost,runningTotal,totalVAT,priceDiscount,totalPriceOfDiscount),null));
    }


    private void updateSaleItemCost(SequenceIterator<SaleItem> iterator) {
            while (!iterator.isOver()) {
                SaleItem saleItem = iterator.getCurrentItem();
                saleItem.reCalcTotalPrice();
                saleItem.reCalcTotalVAT();
                iterator.nextItem();
            }
        iterator.firstItem();
    }
    private void updateSaleCost(CostDetail costDetail, SequenceIterator<SaleItem> iterator) {
        RunningTotal runningTotal = new RunningTotal();
        TotalVAT totalVAT = new TotalVAT();
        BigDecimal totalDiscount = new BigDecimal(0);
        while (iterator.hasNext()){
            SaleItem saleItem = iterator.getCurrentItem();
            runningTotal.increaseValue(saleItem.getTotalPrice());
            totalVAT.increaseValue(saleItem.getTotalVAT());
            totalDiscount = totalDiscount.add(saleItem.getItemDiscount().getTotalPriceReduction());
            iterator.nextItem();
        }
        iterator.firstItem();
        totalDiscount = totalDiscount.add(costDetail.getPriceDiscount().getTotalPriceReduction());
        costDetail.setTotalDiscountOfPrice(totalDiscount);
        runningTotal.decreaseValue(costDetail.getTotalDiscountOfPrice());
        costDetail.setRunningTotal(runningTotal);
        costDetail.setTotalVAT(totalVAT);
    }
}

package service.visitor.costvisitor;

import model.ObservableModel;
import model.amount.RunningTotal;
import model.amount.TotalVAT;
import model.sale.SaleItem;
import model.sale.saleinformation.cost.CostDetail;

import observer.modelobserver.EventObserver;
import observer.modelobserver.ObservedEvent;
import observer.modelobserver.PropertyChangeEvent;
import service.visitor.Visitor;
import util.datatransferobject.CostDTO;
import util.sequence.SequenceIterator;

import java.util.ArrayList;

public class CostVisitor implements Visitor<CostDetail,SequenceIterator<SaleItem>>, ObservableModel {
    private SequenceIterator<SaleItem> iterator;
    private static CostVisitor instance;
    private ArrayList<EventObserver> eventObservers;

    private CostVisitor() {
        eventObservers = new ArrayList<>();
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

    @Override
    public void processElement(CostDetail costDetail) {
        RunningTotal runningTotal = new RunningTotal();
        TotalVAT totalVAT = new TotalVAT();
        iterator.firstItem();

        while(!iterator.isOver()) {
            SaleItem currentItem = iterator.getCurrentItem();
            currentItem.reCalcTotalVAT();
            currentItem.reCalcTotalPrice();
            totalVAT.increaseValue(currentItem.getTotalVAT());
            runningTotal.increaseValue(currentItem.getTotalPrice());
            costDetail.increaseTotalDiscountOfPrice(currentItem.getItemDiscount().getTotalPriceReduction());
            iterator.nextItem();
        }

        costDetail.increaseTotalDiscountOfPrice(costDetail.getPriceDiscount().getTotalPriceReduction());
        runningTotal.decreaseValue(costDetail.getTotalDiscountOfPrice());
        costDetail.setRunningTotal(runningTotal);
        costDetail.setTotalVAT(totalVAT);
        iterator.firstItem();
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

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {
        for(EventObserver eventObserver: eventObservers)
            eventObserver.newEvent(observedEvent);
    }

    @Override
    public void addObserver(EventObserver eventObserver) {
        eventObservers.add(eventObserver);
    }

    @Override
    public void removeObserver(EventObserver eventObserver) {

    }
}

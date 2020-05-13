package service.visitor.cartvisitor;

import model.sale.SaleItem;
import model.sale.saleinformation.ProductCart;
import observer.modelobserver.EventObserver;
import observer.modelobserver.ObservedEvent;
import observer.modelobserver.PropertyChangeEvent;
import service.visitor.Visitor;
import util.sequence.SequenceIterator;

import java.util.ArrayList;
import java.util.Objects;

public class ProductCartVisitor implements Visitor<ProductCart, SaleItem> {
    private SaleItem saleItem;
    private static ProductCartVisitor instance;
    private ArrayList<EventObserver> eventObservers;

    private ProductCartVisitor() {
        eventObservers = new ArrayList<>();
    }

    /**
     * Singleton method used to create an instance of the class
     * and make sure that multiple instances can not be created
     * <code> synchronized </code> keyword is used to make the
     * calls to the method thread safe.
     * * @return
     */
    public static Visitor<ProductCart, SaleItem> getInstance() {
        if (instance == null) {
            synchronized (ProductCartVisitor.class) {
                if (instance == null) {
                    instance = new ProductCartVisitor();
                }
            }
        }
        return instance;
    }

    @Override
    public void setData(SaleItem saleItem) {
        this.saleItem = saleItem;
    }

    @Override
    public void processElement(ProductCart productCart) {
        SequenceIterator<SaleItem> iterator = productCart.sequenceIterator();
        if (Objects.nonNull(saleItem)) {
            boolean hasSaleItem = searchSaleItem(iterator);
            if (hasSaleItem)
                iterator.getCurrentItem().increaseQuantity(saleItem.getQuantity());
            else
                productCart.addItem(saleItem);
        }

        iterator.firstItem();
        updateSaleItemCost(iterator);

        productCart.notifyObservers(new PropertyChangeEvent("list", productCart.getItems(), productCart.getItems()));
    }

    private boolean searchSaleItem(SequenceIterator<SaleItem> iterator) {
        iterator.firstItem();
        while (!iterator.isOver()) {
            if (iterator.getCurrentItem().compare(saleItem))
                return true;
            iterator.nextItem();
        }
        return false;
    }

    private void updateSaleItemCost(SequenceIterator<SaleItem> iterator) {
        while (iterator.hasNext()) {
            SaleItem saleItem = iterator.getCurrentItem();
            saleItem.reCalcTotalPrice();
            saleItem.reCalcTotalVAT();
            iterator.nextItem();
        }
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {
        eventObservers.forEach(eventObserver -> {
            eventObserver.newEvent(observedEvent);
        });
    }

    @Override
    public void addObserver(EventObserver eventObserver) {
        eventObservers.add(eventObserver);
    }

    @Override
    public void removeObserver(EventObserver eventObserver) {

    }
}

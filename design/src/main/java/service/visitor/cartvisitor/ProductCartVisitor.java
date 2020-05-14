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

    /**
     * Override to process <code> ProductCart </code> object
     * that contains items bought during a sale transaction.
     *
     * Adds sale items to a <code> ProductCart </code> object,
     * if the cart contains the sale item that is to be added
     * it increases the quantity instead.
     *
     * Updates the cost of each sale item in the cart before
     * returning to the caller.
     *
     * can be used to only update the cost of the sale items
     * if the inherited <code> data </code> field is set to
     * null before calling.
     * @param productCart
     */
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

}

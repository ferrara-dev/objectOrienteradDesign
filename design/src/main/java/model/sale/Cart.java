package model.sale;

import integration.productdb.Product;
import model.ObservableModel;
import model.discount.discounttypes.itemdiscount.ItemDiscount;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
import util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class representing the a sale cart where registered <code> SaleItem </code>
 * are stored during the sales process.
 * Implements <code> ObservableModel </code> interface to allow the view to
 * listen to changes in the model.
 */
public class Cart implements ObservableModel {
    private ArrayList<SaleItem> items;
    private ArrayList<EventObserver> eventObservers = new ArrayList<>();
    private int listSize = 0;

    public Cart() {
        items = new ArrayList<>();

    }

    /**
     * Add a discount to an item specified by the <code> itemId </code>
     *
     * @param itemId       identifies the item that the discount is set to
     * @param itemDiscount the discount that is applied
     */
    public void addItemDiscount(int itemId, ItemDiscount itemDiscount) {
        if (itemDiscount == null)
            return;
        getItem(itemId).setItemDiscount(itemDiscount);
    }

    /**
     * Getter, gets the list of items that has been registered
     *
     * @return
     */
    public ArrayList<SaleItem> getItems() {
        return items;
    }

    /**
     * Gets the size of <code> list </code>
     *
     * @return the size of the sale item list
     */
    public int size() {
        if (Objects.nonNull(items))
            return items.size();
        return 0;
    }
    /**
     * add a product to the cart.
     * Calls local method <code> contains() </code> to
     * check if the product already has been registered
     * to the sale.
     * @param saleItem
     */
    public void addProduct(SaleItem saleItem) {
        ArrayList<SaleItem> oldValue = items;
        if (Objects.nonNull(items) && Objects.nonNull(saleItem)) {
            if (!contains(saleItem.getProduct().getItemId()))
                items.add(saleItem);
            else {
                int quantity = saleItem.getQuantity();
                get(saleItem.getProduct()).update(quantity);
            }
        }
        notifyObservers(new PropertyChangeEvent("items", items, oldValue));
    }

    /**
     * Method to get an saleitem from the cart
     * if the cart does not contain the item
     * in question, NotFoundException is thrown
     * already contain a product
     *
     * @param product
     * @return
     */
    public SaleItem get(Product product) {
        if (Objects.nonNull(product))
            if (Objects.nonNull(items))
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getProduct().equals(product)) {
                        return items.get(i);
                    }
                }
        throw new NotFoundException("not found in cart");
    }

    /**
     * Method to check is the list <code> items </code>
     * already contain a product
     *
     * @param saleItem
     * @return
     */
    public boolean contains(SaleItem saleItem) {
        if (Objects.nonNull(items))
            for (SaleItem saleItem1 : items) {
                if (saleItem1.getProduct().equals(saleItem.getProduct())) {
                    return true;
                }
            }
        return false;
    }

    /**
     * Method to check is the list <code> items </code>
     * already contain a product
     *
     * @param itemId
     * @return
     */
    public boolean contains(int itemId) {
        if (Objects.nonNull(items))
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getProduct().getItemId() == itemId) {
                    return true;
                }
            }
        return false;
    }

    public SaleItem getItem(int itemId) {
        if (Objects.nonNull(items))
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getProduct().getItemId() == itemId) {
                    return items.get(i);
                }
            }
        throw new NotFoundException("not found in cart");
    }

    /**
     * Get a sale item from <code> items </code> list
     * <p>
     * if the index is larger than the highest indexed item,
     * the highest indexed item will be returned
     * <p>
     * if the index i lower than 0, the first item in the list
     * will be returned.
     *
     * @param index
     * @return
     */
    public SaleItem get(int index) {
        if (index > items.size() - 1)
            index = items.size() - 1;
        else if (index < 0)
            index = 0;
        return items.get(index);
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {
        eventObservers.forEach(eventObserver -> {
            eventObserver.stateHasChanged(observedEvent);
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

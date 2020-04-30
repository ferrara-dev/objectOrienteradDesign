package model.sale;
import integration.productdb.Product;
import model.ObservableModel;
import model.discount.discounttypes.itemdiscount.ItemDiscount;
import model.discount.discounttypes.defaultdiscount.NoItemDiscount;
import observer.EventObserver;
import observer.ObservedEvent;
import util.exception.IllegalDiscountCombinationException;

import java.util.Objects;

public class SaleItem implements ObservableModel {
    private final Product product;
    private ItemDiscount itemDiscount;
    private double totalPrice;
    private double totalVAT;
    private int quantity;

    public SaleItem(Product product, int quantity){
        this.product = product;
        itemDiscount = new NoItemDiscount();
        this.quantity = quantity;
        totalVAT = product.getTotalVAT() * quantity;
        totalPrice = product.getTotalPrice() * quantity;
    }

    /**
     * Set the item discount
     * if <code> itemDiscount == null </code> or <code> this.itemDiscount</code>
     * has a lower price reduction the <code> @param itemDiscount </code> the
     * discount is set to the value of <code> @param itemDiscount </code>.
     * else <code> IllegalDiscountCombinationException </code> is thrown
     * and needs to be handled by the caller.
     * @param itemDiscount the discount that is to be applied to the saleItem.
     */
    public void setItemDiscount(ItemDiscount itemDiscount){
        if(Objects.nonNull(itemDiscount))
            if( (this.itemDiscount.getTotalPriceReduction() < itemDiscount.getTotalPriceReduction())) {
            this.itemDiscount = itemDiscount;

        }

        else
            throw new IllegalDiscountCombinationException();
    }

    public ItemDiscount getItemDiscount() {
        return itemDiscount;
    }

    /**
     * Update the saleitems quantity, price and total vat
     * @param increasedQuantity
     */
    public void update(int increasedQuantity){
        if(Objects.nonNull(this) && Objects.nonNull(product)) {
            int newQuantity = quantity + increasedQuantity;
            double newTotalPrice = product.getTotalPrice() * newQuantity;
            double newTotalVAT = product.getTotalVAT() * newQuantity;
            setQuantity(newQuantity);

            if(itemDiscount != null){
                newTotalPrice = newTotalPrice - itemDiscount.getTotalPriceReduction();
            }

            setTotalPrice(newTotalPrice);
            setTotalVAT(newTotalVAT);

        }
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalVAT(double totalVAT) {
        this.totalVAT = totalVAT;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {

    }

    @Override
    public void addObserver(EventObserver eventObserver) {

    }

    @Override
    public void removeObserver(EventObserver eventObserver) {

    }
}

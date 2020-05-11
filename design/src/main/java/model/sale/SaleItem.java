package model.sale;

import integration.productdb.Product;
import model.ObservableModel;
import model.discount.discounttypes.itemdiscount.ItemDiscount;
import model.discount.discounttypes.defaultdiscount.NoItemDiscount;
import observer.EventObserver;
import observer.ObservedEvent;
import util.exception.businessruleexception.IllegalDiscountCombinationException;

import java.math.BigDecimal;
import java.util.Objects;

public class SaleItem implements ObservableModel {
    private final Product product;
    private ItemDiscount itemDiscount;
    private BigDecimal totalPrice;
    private BigDecimal totalVAT;
    private int quantity;

    public SaleItem(Product product, int quantity) {
        this.product = product;
        itemDiscount = new NoItemDiscount();
        this.quantity = quantity;
        totalVAT = new BigDecimal(product.getTotalVAT() * quantity);
        totalPrice = new BigDecimal(product.getTotalPrice() * quantity);
    }

    /**
     * Set the item discount
     * if <code> itemDiscount == null </code> or <code> this.itemDiscount</code>
     * has a lower price reduction the <code> @param itemDiscount </code> the
     * discount is set to the value of <code> @param itemDiscount </code>.
     * else <code> IllegalDiscountCombinationException </code> is thrown
     * and needs to be handled by the caller.
     *
     * @param itemDiscount the discount that is to be applied to the saleItem.
     */
    public void setItemDiscount(ItemDiscount itemDiscount) {
        if (Objects.nonNull(itemDiscount))
            if ((this.itemDiscount.getTotalPriceReduction().doubleValue() < itemDiscount.getTotalPriceReduction().doubleValue())) {
                this.itemDiscount = itemDiscount;

            } else
                throw new IllegalDiscountCombinationException();
    }

    public ItemDiscount getItemDiscount() {
        return itemDiscount;
    }

    public boolean compare(SaleItem saleItem) {
        if (this.product.equals(saleItem.getProduct()))
            return true;
        else return false;
    }

    /**
     * Increase the sale items quantity by adding a
     * another quantity
     * @param quantity, the quantity that this <code> quantity </code>
     *                  is increased by.
     */
    public void increaseQuantity(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    public void reCalcTotalPrice() {
        totalPrice = new BigDecimal(product.getTotalPrice() * quantity - itemDiscount.getTotalPriceReduction().doubleValue());
    }

    public void reCalcTotalVAT() {
        totalVAT = new BigDecimal(product.getTotalVAT() * quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = new BigDecimal(totalPrice);
    }

    public void setTotalVAT(double totalVAT) {
        this.totalVAT = new BigDecimal(totalVAT);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getTotalVAT() {
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

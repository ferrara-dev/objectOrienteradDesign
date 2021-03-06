package factory.builderpattern;

import factory.Builder;
import model.sale.Date;
import model.sale.SaleId;
import model.sale.saleinformation.cost.CostDetail;
import model.sale.saleinformation.ProductCart;
import model.sale.saleinformation.SaleSpecification;
import model.sale.saleinformation.salestate.SaleState;

/**
 * Implementation of the <code> Builder </code> interface.
 * Implements a builder pattern used to construct a
 * new <code> SaleSpecification </code> att program startup.
 */
public class SaleSpecBuilder implements Builder<SaleSpecification> {
    private Date dateOfSale;
    private SaleId saleId;
    private SaleState saleState;
    private ProductCart cart;
    private CostDetail cost;


    public SaleSpecBuilder(){

    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public SaleSpecBuilder setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
        return this;
    }

    public SaleId getSaleId() {
        return saleId;
    }

    public SaleSpecBuilder setSaleId(SaleId saleId) {
        this.saleId = saleId;
        return this;
    }

    public SaleState getSaleState() {
        return saleState;
    }

    public SaleSpecBuilder setSaleState(SaleState saleState) {
        this.saleState = saleState;
        return this;
    }

    public ProductCart getCart() {
        return cart;
    }

    public SaleSpecBuilder setCart(ProductCart cart) {
        this.cart = cart;
        return this;
    }

    public CostDetail getCost() {
        return cost;
    }

    public SaleSpecBuilder setCost(CostDetail cost) {
        this.cost = cost;
        return this;
    }

    /**
     * assemble the SaleSpecification and return it.
     * @return the <code> SaleSpecification </code> object
     *         that has been built.
     */
    @Override
    public SaleSpecification build() {
        SaleSpecification saleSpecification = new SaleSpecification();
        saleSpecification.create(this);
        return saleSpecification;
    }
}

package model.sale.saleinformation;

import factory.builderpattern.SaleSpecBuilder;
import model.sale.Date;
import model.sale.SaleId;
import model.Element;
import model.sale.saleinformation.cost.CostDetail;
import model.sale.saleinformation.salestate.SaleState;
import service.visitor.Visitor;
import util.exception.notfoundexception.NotFoundException;

public class SaleSpecification implements Element {
    private Date dateOfSale;
    private SaleId saleId;
    private SaleState saleState;
    private ProductCart cart;
    private CostDetail cost;

    /**
     * Empty constructor in order
     * to be able to store the
     * object in JSON format
     */
    public SaleSpecification(){

    }

    public void create(SaleSpecBuilder saleSpecBuilder){
        this.dateOfSale = saleSpecBuilder.getDateOfSale();
        this.cart = saleSpecBuilder.getCart();
        this.saleId = saleSpecBuilder.getSaleId();
        this.saleState = saleSpecBuilder.getSaleState();
        this.cost = saleSpecBuilder.getCost();
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public SaleId getSaleId() {
        return saleId;
    }

    public void setSaleId(SaleId saleId) {
        this.saleId = saleId;
    }

    public SaleState getSaleState() {
        return saleState;
    }

    public void setSaleState(SaleState saleState) {
        this.saleState = saleState;
    }

    public ProductCart getCart() {
        return cart;
    }

    public void setCart(ProductCart cart) {
        this.cart = cart;
    }

    public CostDetail getCost() {
        return cost;
    }

    public void setCost(CostDetail cost) {
        this.cost = cost;
    }

    @Override
    public void acceptVisitor(Visitor visitor) throws NotFoundException {
        visitor.processElement(this);
    }
}

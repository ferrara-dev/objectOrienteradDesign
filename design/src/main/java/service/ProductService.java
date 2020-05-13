package service;

import integration.DataBaseHandler;
import factory.IntegrationFactory;
import model.sale.SaleItem;
import model.sale.saleinformation.ProductCart;
import model.sale.saleinformation.SaleTransaction;
import integration.productdb.Product;
import service.visitor.Visitor;
import factory.VisitorFactory;
import util.datatransferobject.SaleItemDTO;

/**
 * Performs operations to add new items and update the cost of a <code>SaleTransaction </code>
 * Fetches product information from the <code> ProductRepository </code> to create sale items.
 *
 * This service class uses implementations of the <code> Visitor </code> interface to perform
 * manipulation on objects from the model layer.
 */

public class ProductService{
    private DataBaseHandler<Product, Object> productRepo;
    private Visitor visitor;

    public ProductService() {
        productRepo = IntegrationFactory.PRODUCT_REPO.getDataBaseHandler();
    }

    /**
     * Register a new product to the sale
     * <p>
     * A product will be collected by calling <code> productRepo </code> and
     * then added to the <code> cart </code>.
     *
     * If the item id is invalid, or connection to database fails for some
     * reason when calling the private method <code> createSaleItem </code>,
     * A runtimeException that will be caught in the service facade and sent
     * to the <Code> ExceptionHandler </Code> class will be thrown in the product repository.
     *
     * @param saleItemDTO     information about the product that is added to the
     *                        cart.
     * @param saleTransaction the saleTransaction that the saleItem is registered to.
     *
     */
    public void registerProduct(SaleItemDTO saleItemDTO, SaleTransaction saleTransaction) {
        SaleItem saleItem = createSaleItem(saleItemDTO);
        addSaleItem(saleTransaction, saleItem);
    }

    private SaleItem createSaleItem(SaleItemDTO saleItemDTO){
        String itemId = String.valueOf(saleItemDTO.getItemId());
        int quantity = saleItemDTO.getQuantity();
        Product product = productRepo.collect(String.valueOf(saleItemDTO.getItemId()));
        SaleItem saleItem = new SaleItem(product, saleItemDTO.getQuantity());
        return saleItem;
    }

    private void addSaleItem(SaleTransaction saleTransaction, SaleItem saleItem){
        ProductCart productCart = saleTransaction.getCart();
        visitor = VisitorFactory.PRODUCT_CART_VISITOR.getVisitor();
        visitor.setData(saleItem);
        productCart.acceptVisitor(visitor);
        updateSaleCost(saleTransaction);
    }

    private void updateSaleCost(SaleTransaction saleTransaction){
        visitor = VisitorFactory.COST_VISITOR.getVisitor();
        visitor.setData(saleTransaction.getCart().sequenceIterator());
        saleTransaction.getCost().acceptVisitor(visitor);
    }
}

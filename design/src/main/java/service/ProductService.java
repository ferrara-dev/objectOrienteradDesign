package service;

import integration.DataBaseHandler;
import factory.IntegrationFactory;
import model.sale.SaleItem;
import model.transaction.saleTransaction.SaleTransaction;
import service.handlerpattern.exceptionhandler.ExceptionHandlingFactory;
import integration.productdb.Product;
import service.visitor.Visitor;
import factory.VisitorFactory;
import util.datatransferobject.SaleItemDTO;
import util.exception.DataBaseAccessFailureException;
import util.exception.notfoundexception.NotFoundException;

/**
 * Performs logic to create and fetch products from the database and
 * update the inventory when a sale is finalized
 */

public class ProductService implements Service {
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
     * @param saleItemDTO     information about the product that is added to the
     *                        cart.
     * @param saleTransaction the saleTransaction that the saleItem is registered to.
     */
    public void registerProduct(SaleItemDTO saleItemDTO, SaleTransaction saleTransaction) {
        Product product = null;
        try {
            product = productRepo.collect(String.valueOf(saleItemDTO.getItemId()));
        } catch (NotFoundException | DataBaseAccessFailureException e) {
            ExceptionHandlingFactory.getExceptionHandlingChain().handle(e);
            return;
        }

        SaleItem saleItem = new SaleItem(product, saleItemDTO.getQuantity());
        visitor = VisitorFactory.PRODUCT_CART_VISITOR.getVisitor();
        visitor.setData(saleItem);
        saleTransaction.getCart().acceptVisitor(visitor);
        visitor = VisitorFactory.COST_VISITOR.getVisitor();
        visitor.setData(saleTransaction.getCart().getSequenceIterator());
        saleTransaction.getCost().acceptVisitor(visitor);
    }

}

package service.modelservice.productservice;

import integration.DataBaseHandler;
import factory.IntegrationFactory;
import model.sale.SaleItem;
import model.transaction.saleTransaction.SaleTransaction;
import service.modelservice.Service;
import integration.productdb.Product;
import service.visitor.Visitor;
import factory.VisitorFactory;
import util.datatransferobject.SaleItemDTO;
import util.exception.notfoundexception.NotFoundException;
import util.exception.notfoundexception.ProductNotFoundException;

import java.util.List;

/**
 * Performs logic to create and fetch products from the database and
 * update the inventory when a sale is finalized
 */

public class ProductService implements Service {
    private DataBaseHandler<Product, Object> productRepo;
    private Visitor visitor;

    public ProductService(){

        productRepo = IntegrationFactory.PRODUCT_REPO.getDataBaseHandler();
    }

    /**
     * Update the inventory stock status for all items
     * that have been sold in a sale
     *
     * @param soldItems
     */
    public void updateInventory(List<SaleItem> soldItems) {
        int oldStock;
        int newStock;
        for (SaleItem saleItem : soldItems) {
            oldStock = saleItem.getProduct().getStockstatus();
            newStock = oldStock - saleItem.getQuantity();
            saleItem.getProduct().setStockstatus(newStock);
            int id = saleItem.getProduct().getItemId();
            productRepo.register(String.valueOf(id), saleItem.getProduct());
        }
    }

    /**
     * Register a new product to the sale
     *
     * A product will be collected by calling <code> productRepo </code> and
     * then added to the <code> cart </code>.
     *
     * @param saleItemDTO information about the product that is added to the
     *                    cart.
     * @param saleTransaction the saleTransaction that the saleItem is registered to.
     */
    public void registerProduct(SaleItemDTO saleItemDTO, SaleTransaction saleTransaction) throws ProductNotFoundException {
            Product product = null;
            try {
                product = productRepo.collect(String.valueOf(saleItemDTO.getItemId()));
                SaleItem saleItem = new SaleItem(product,saleItemDTO.getQuantity());
                visitor = VisitorFactory.PRODUCT_CART_VISITOR.getVisitor();
                visitor.setData(saleItem);
                saleTransaction.getCart().acceptVisitor(visitor);
                visitor =  VisitorFactory.COST_VISITOR.getVisitor();
                visitor.setData(saleTransaction.getCart().getSequenceIterator());
                saleTransaction.getCost().acceptVisitor(visitor);
            } catch (NotFoundException notFoundException){
                throw new ProductNotFoundException(notFoundException,saleItemDTO);
            }


    }
}

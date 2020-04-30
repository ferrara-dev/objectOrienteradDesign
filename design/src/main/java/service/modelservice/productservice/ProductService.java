package service.modelservice.productservice;

import model.sale.SaleItem;
import service.modelservice.Service;
import startup.layer.ServiceCreator;
import integration.productdb.Product;
import service.IntegrationService;

import java.util.List;

/**
 * Performs logic to create fetch products from the database and
 * update the inventory when a sale is finalized
 */
public class ProductService implements Service {
    IntegrationService<Product> integrationService;

    public ProductService(ServiceCreator serviceCreator){
        integrationService = serviceCreator.getIntegrationServiceCreator().getProductDBService();
    }

    /**
     * Update the inventory stock status for all items
     * that have been sold in a sale
     * @param soldItems
     */
    public void updateInventory(List<SaleItem> soldItems){
        int oldStock;
        int newStock;
        for(SaleItem saleItem: soldItems){
            oldStock = saleItem.getProduct().getStockstatus();
            newStock = oldStock - saleItem.getQuantity();
            saleItem.getProduct().setStockstatus(newStock);
            integrationService.updateDB(saleItem.getProduct());
        }
    }

    /**
     * Fetch an item from the product database
     * @param itemId
     * @return
     */
    public Product getProduct(int itemId){
        return integrationService.getFromDB(itemId);
    }

    @Override
    public Service getInstance() {
        return null;
    }
}

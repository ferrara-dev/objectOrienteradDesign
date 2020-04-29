package service.modelservice.productservice;

import model.sale.SaleItem;
import service.modelservice.Service;
import startup.layer.ServiceCreator;
import integration.productdb.Product;
import service.IntegrationService;

import java.util.List;

public class ProductService implements Service {
    IntegrationService<Product> integrationService;

    public ProductService(ServiceCreator serviceCreator){
        integrationService = serviceCreator.getIntegrationServiceFactory().getProductDBService();
    }

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
    public Product getProduct(int itemId){
        return integrationService.getFromDB(itemId);
    }

    @Override
    public Service getInstance() {
        return null;
    }
}

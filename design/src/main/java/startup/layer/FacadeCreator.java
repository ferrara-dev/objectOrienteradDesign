package startup.layer;

import service.facadepattern.SaleTransactionService;
import service.ServiceFacade;
import service.facadepattern.EconomyService;

public class FacadeCreator {
    ServiceFacade serviceFacade;
    SaleTransactionService saleTransactionService;
    EconomyService economyService;

    public FacadeCreator(ServiceCreator serviceCreator){
        saleTransactionService = new SaleTransactionService(serviceCreator);

    }
}

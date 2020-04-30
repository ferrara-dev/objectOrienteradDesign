package controller.subcontroller;

import service.modelservice.customerservice.CustomerService;

public class CustomerController {
    CustomerService customerService;

    /**
     * Customer controller, not used yet.
     * TODO: implement methods to initiate registration of a new member
     * @param customerService
     */
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
}

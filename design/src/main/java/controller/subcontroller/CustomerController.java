package controller.subcontroller;

import service.modelservice.customerservice.CustomerService;

public class CustomerController {
    CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;

    }
}

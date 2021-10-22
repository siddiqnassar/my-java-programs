package com.hibernate.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.customer.model.CustomerModel;
import com.hibernate.customer.service.CustomerService;

@RestController
public class CustomerController {


    @Autowired
    CustomerService customerService;
    
    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET, headers = "Accept=application/json")
    public String getAllCustomers() {
       return customerService.getAllCustomers().toString();
    }
    
    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST, headers = "Accept=application/json")
    public String addCustomer(@RequestBody CustomerModel customer) {
        return customerService.addCustomer(customer);
    }
}

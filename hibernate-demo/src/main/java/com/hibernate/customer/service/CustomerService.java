package com.hibernate.customer.service;
import com.hibernate.customer.dao.CustomerDao;
import com.hibernate.customer.dao.CustomerDaoImpl;
import com.hibernate.customer.model.*;

import java.util.List;
import com.hibernate.customer.model.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    @Autowired
    CustomerDaoImpl customerDaoImpl;
    
    @Transactional
    public String getAllCustomers() {
        return customerDaoImpl.getAllCustomers();
    }
    @Transactional
    public String addCustomer(CustomerModel customer) {
       return customerDaoImpl.addCustomer(customer);
    }
}

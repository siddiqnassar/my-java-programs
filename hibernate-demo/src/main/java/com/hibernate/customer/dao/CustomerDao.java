package com.hibernate.customer.dao;

import java.util.List;
import com.hibernate.customer.model.CustomerModel;

public interface CustomerDao {

    public List<CustomerModel> getAllCustomers();
    public String addCustomer(CustomerModel customer);
}

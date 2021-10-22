package com.hibernate.customer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hibernate.customer.model.CustomerModel;
import com.hibernate.customer.model.AddCustomerResponse;;

@Repository
public class CustomerDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private AddCustomerResponse addCustomerResponse;
    
    
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    

    @SuppressWarnings("unchecked")
    public String getAllCustomers() {
        Session session = this.sessionFactory.getCurrentSession();
        //native SQL QUERY
        SQLQuery query = session.createSQLQuery("select * from CUSTOMER;");
        List<Object[]> rows = query.list();
        //HQL QUERY
//        List<CustomerModel>  customerList = session.createQuery("from CustomerModel").list();
//        System.out.println("customerlist is "+customerList.toString());
        for(Object[] row : rows){
            CustomerModel customer = new CustomerModel();
            customer.setId(Integer.parseInt(row[0].toString()));
            customer.setCustomerName(row[1].toString());
            customer.setEmail(row[2].toString());
            System.out.println(customer);
        }
//        return customerList.toString();
        return rows.toString();
    }
    
    public String addCustomer(CustomerModel customer) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.println("data to be saved is "+customer);
        Serializable savedId = session.save(customer);
        System.out.println(savedId);
        
        if(savedId != null) {
            addCustomerResponse.setInfo("added Data successfully");
            addCustomerResponse.setIsSuccess(true);
        } else {
            addCustomerResponse.setInfo("unable to add data");
            addCustomerResponse.setIsSuccess(false);
        }
        return addCustomerResponse.toString();
        
    }
}

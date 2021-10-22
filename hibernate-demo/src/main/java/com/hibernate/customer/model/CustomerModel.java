package com.hibernate.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class CustomerModel {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
 
    @Column(name="customerName")
    String customerName; 
 
    @Column(name="email")
    String email;
 
    public CustomerModel() {
        super();
    }
    public CustomerModel(String customerName,String email) {
        super();
        this.customerName=customerName;
        this.email=email;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "{ \'id\' :" + id + ", \'customerName\':" + customerName + ", \'email\' :" + email + "}";
    }
 
}

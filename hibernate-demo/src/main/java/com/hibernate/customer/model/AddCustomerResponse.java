package com.hibernate.customer.model;

import org.springframework.stereotype.Component;

@Component
public class AddCustomerResponse {

    private String info;
    private Boolean isSuccess;
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public Boolean getIsSuccess() {
        return isSuccess;
    }
    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    @Override
    public String toString() {
        return "{ \'info\' :" + info + ", \'isSuccess\':" + isSuccess + "}";
    }
    
}

package com.loginservice.securingmicroservices.model;

 

public class OTPRequest {
    private Long userID;
    private String otp;
    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }
    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }
    
}
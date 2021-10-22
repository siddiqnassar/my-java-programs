package com.locationsimulator.model;

import org.springframework.stereotype.Component;

@Component
public class ErrorModel {

    private String info;
    private Exception exception;
    public Exception getException() {
        return exception;
    }
    public void setException(Exception e) {
        this.exception = e;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "{\"info\": \"" + info + "\",\"exception\": \"" + exception +  "\"}";
    }

    
}

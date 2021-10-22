package com.locationsimulator.service;

import java.util.Base64;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ConnectVendor {

    @Value("${app.apiKey}")
    String apiKey;
    
    @Value("${app.url}")
    String mapUrl;
    
    @Autowired
    private RestTemplate restTemplate;
    public String locationResults(String origin, String destination) {
       String url = mapUrl + "origin=" + origin + "&destination=" + destination +"&key=" + apiKey;
       return restTemplate.getForObject(url, String.class); 
    }
}

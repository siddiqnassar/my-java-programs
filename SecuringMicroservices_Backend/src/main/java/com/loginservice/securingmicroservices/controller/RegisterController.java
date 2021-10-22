package com.loginservice.securingmicroservices.controller;

 

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.loginservice.securingmicroservices.config.JWTtokenUtil;
import com.loginservice.securingmicroservices.model.AccountStatus;
import com.loginservice.securingmicroservices.model.JwtRequest;
import com.loginservice.securingmicroservices.model.User;

 


@RestController
@CrossOrigin("*")
public class RegisterController {
	ResourceBundle mybundle = ResourceBundle.getBundle("registercontroller");
	private RestTemplate restTemplate=new RestTemplate();
    
    
    @Autowired
    private JWTtokenUtil jwtTokenUtil;
    
    private Logger log = LoggerFactory.getLogger(SecuringMicroservicecontroller.class);

 
    @CrossOrigin("*")
    @GetMapping("/check")
    public ResponseEntity<String> check(HttpServletRequest htp){
        
        String token=htp.getHeader("Authorization").substring(7);
        log.info("inside checking api");
        log.info(token);

 

        return new ResponseEntity<>(HttpStatus.OK);

 

    }
    
    @CrossOrigin("*")
    @PostMapping("/register")
    public ObjectNode register(@RequestBody User u) {
        String url = mybundle.getString("RegisterController.registerurl");
        ObjectNode obj = restTemplate.postForObject(url, u, ObjectNode.class);
        log.info("{}",obj);
        log.info("{}","in securing micro services");
        //System.out.println(obj.get("status").getClass());
        if(u.getAccountStatus()==AccountStatus.ACTIVE && obj.get("status").asInt()==200){
            JwtRequest jwr = new JwtRequest();
            jwr.setEmailID(obj.get("email").asText());
            System.out.println(obj.get("userID").getClass());
            String token = jwtTokenUtil.generateToken(jwr,obj.get("userID").asLong(),Integer.parseInt(mybundle.getString("RegisterController.flag")));
            log.info(token);
            log.info(jwtTokenUtil.getUserIDFromToken(token));
            obj.put("token", token);
        }
        return obj;
    }
    
    
    @CrossOrigin("*")
    @PostMapping("/userconfirmmail")
    public ObjectNode userConfirmationMail(@RequestBody User u) {
        String url = mybundle.getString("RegisterController.userconfirmationmailurl");
        System.out.println(u);
        ObjectNode obj = restTemplate.postForObject(url,u.getUserID(), ObjectNode.class);
        System.out.println(obj);
        return obj;
        
    }
}
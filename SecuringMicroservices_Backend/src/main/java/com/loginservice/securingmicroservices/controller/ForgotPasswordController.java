package com.loginservice.securingmicroservices.controller;

 

import java.util.ResourceBundle;

 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

 

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.loginservice.securingmicroservices.WebSecurityConfig;
import com.loginservice.securingmicroservices.config.JWTtokenUtil;
import com.loginservice.securingmicroservices.model.JwtRequest;
import com.loginservice.securingmicroservices.model.JwtResponse;
import com.loginservice.securingmicroservices.model.Request;
import com.loginservice.securingmicroservices.model.User;
import com.thoughtworks.xstream.mapper.Mapper;

 

@RestController
@CrossOrigin
public class ForgotPasswordController {

 

    private Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

 

    @Autowired
    private JWTtokenUtil jwtTokenUtil;
    ResourceBundle mybundle = ResourceBundle.getBundle("forgotpasswordcontroller");

 

    @Autowired
    private RestTemplate resttemplate;
    
    @Autowired
    private ObjectMapper mapper;

 

    @CrossOrigin(origins = "*")

 

    @RequestMapping(value = "/gettoken", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
        System.out.println("yoyo");
        String url = mybundle.getString("ForgotPasswordController.uicurl");
        System.out.println(authenticationRequest);
        // authenticationRequest.setUserId();
        String ls = resttemplate.postForObject(url, authenticationRequest, String.class);
        System.out.println(ls);
        JsonObject convertedObject = new Gson().fromJson(ls, JsonObject.class);
        System.out.println(authenticationRequest.getEmailID());
        String stat = convertedObject.get("status").getAsString();
        long id = convertedObject.get("userId").getAsInt();

 

        System.out.println(convertedObject.get("status").getAsString());
        System.out.println(convertedObject.get("userId").getAsInt());

 

        if (stat.equals("true")) {
            System.out.println("valid" + id);
            final String token = jwtTokenUtil.generateToken(authenticationRequest,(long) id,0);
            log.info(token);
            log.info(jwtTokenUtil.getUserIDFromToken(token));
            return ResponseEntity.ok(new JwtResponse(token));
        }

 

        return ResponseEntity.ok(new JwtResponse("Invalid "));
    }
    @CrossOrigin(origins = "*")

 

    @RequestMapping(value = "/uic", method = RequestMethod.POST)
    public String getValidation(@RequestBody User user)
            throws Exception {
        String url = mybundle.getString("ForgotPasswordController.uicurl");

 

        return resttemplate.postForObject(url, user, String.class);
        

 

    
        
    }
    @CrossOrigin(origins = "*")

 

    @RequestMapping(value = "/mts", method = RequestMethod.POST)
    public String methodToSet(@RequestBody Request jwtreq)
            throws Exception {
    
        String url = mybundle.getString("ForgotPasswordController.mtsurl");
        System.out.println(jwtreq.getEmail());
        
        return resttemplate.postForObject(url, jwtreq, String.class);
        

 

    
        
    }
    @CrossOrigin(origins = "*")

 

    @RequestMapping(value = "/otp", method = RequestMethod.POST)
    public String validateotp(@RequestBody Request jwtreq)
            throws Exception {
    
        String url = mybundle.getString("ForgotPasswordController.otpurl");
        System.out.println(jwtreq.getEmail());
        
        return resttemplate.postForObject(url, jwtreq, String.class);
        

 

    
        
    }
    @CrossOrigin(origins = "*")

 

    @RequestMapping(value = "/sec", method = RequestMethod.POST)
    public String security(@RequestBody Request jwtreq)
            throws Exception {
    
        String url = mybundle.getString("ForgotPasswordController.securl");
        System.out.println("the jwtRequest object is "+jwtreq);
        
        return resttemplate.postForObject(url, jwtreq, String.class);
        

 

    
        
    }
    @CrossOrigin(origins = "*")

 

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public String setPassword(@RequestBody Request jwtreq)
            throws Exception {
    
        String url =  mybundle.getString("ForgotPasswordController.seturl");
        System.out.println(jwtreq.getEmail());
    
        return resttemplate.postForObject(url, jwtreq, String.class);
        
    }
    @CrossOrigin(origins = "*")

    @RequestMapping(value = "/validatetoken", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> verifying(@RequestBody String token ) {
        System.out.println(token);
        String t = token.substring(11,token.length()-2 );
        System.out.println(t);
        ObjectNode obj = mapper.createObjectNode();
        
        try {
      
        if(!(jwtTokenUtil.isTokenExpired(t)))
        {
            
            System.out.println("not expired");
            String email = jwtTokenUtil.getUsernameFromToken(t);
            System.out.println(email);
            obj.put("email", email);
            obj.put("status", true);
            System.out.println(obj);
            return ResponseEntity.ok(obj);
        }
        
        }
        catch (Exception e) {
            // TODO: handle exception
            obj.put("status", false);
            return ResponseEntity.ok(obj);  
        }
        return ResponseEntity.ok(obj);     
      
    }

 

}
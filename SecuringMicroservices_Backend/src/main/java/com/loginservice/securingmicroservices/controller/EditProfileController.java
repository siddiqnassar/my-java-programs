package com.loginservice.securingmicroservices.controller;

 

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

 

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.loginservice.securingmicroservices.config.JWTtokenUtil;
import com.loginservice.securingmicroservices.model.User;

 

@RestController

 

@CrossOrigin(origins = "*")
public class EditProfileController {
    @Autowired
    private RestTemplate restTemplate;

	ResourceBundle mybundle = ResourceBundle.getBundle("editprofilecontroller");

@Autowired
private JWTtokenUtil jwttokenutil;
    @CrossOrigin(origins = "*", allowedHeaders = "*")



    @GetMapping(value = "/api/editprofile")
    public ResponseEntity<?> first(HttpServletRequest htp) {

    	
    	String token = htp.getHeader("Authorization").substring(7);


		String id = jwttokenutil.getUserIDFromToken(token);
		Long userID = Long.parseLong(id);
        String url = mybundle.getString("EditProfileController.getdetailsurl")+userID;

 

        return restTemplate.getForEntity(url, String.class);
    }

 

    @PostMapping(value = "/api/updateuser")
    public ResponseEntity<?> updateUser(@RequestBody User user) {

 

        String url = mybundle.getString("EditProfileController.updateuserurl");

 System.out.println(user);

        ResponseEntity<String> u = restTemplate.postForEntity(url, user, String.class);

 
System.out.println(u);
        return ResponseEntity.ok(u);

 

    }

 

    @PostMapping(value = "/api/sendotp")
    public ResponseEntity<?> sendOtp(@RequestBody User user,HttpServletRequest htp) {
        String url = mybundle.getString("EditProfileController.sendotpurl");
    	String token = htp.getHeader("Authorization").substring(7);

       
		String id = jwttokenutil.getUserIDFromToken(token);
		String email=jwttokenutil.getUsernameFromToken(token);
		Long userID = Long.parseLong(id);
		user.setUserID(userID);
		user.setEmailID(email);
		String role=jwttokenutil.getUserRoleFromToken(token);
		if(role.equals("admin"))
		{
       return updateUser(user);
		}

	String u=restTemplate.postForObject(url, user, String.class);

        return ResponseEntity.ok(u);
    }

 

    @PostMapping(value = "/api/verifyotp")
    public ResponseEntity<?> verifyOtp(@RequestBody String otp) {
        String url = mybundle.getString("EditProfileController.verifyotpurl");
        
        ResponseEntity<String> u = restTemplate.postForEntity(url, otp, String.class);

 

        return ResponseEntity.ok(u);

 

    }

 

}
package com.loginservice.securingmicroservices.controller;


import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.loginservice.securingmicroservices.model.User;


@RestController
@CrossOrigin
public class DeleteUserServiceContoller {
	
	ResourceBundle mybundle = ResourceBundle.getBundle("deleteusercontroller");


	@Autowired
	private RestTemplate resttemplate;
	@CrossOrigin(origins="*")

	@PostMapping(value = "/deleteuser/")
	public boolean deleteUser(@RequestBody User u)  {
		
		
	    String url=mybundle.getString("DeleteUserController.deleteuserurl");
	   

	  boolean status=resttemplate.postForObject(url,u,Boolean.class);
	 
	  return status;
	    
	    
	   
		
	
	}
	@CrossOrigin(origins="*")
	
	@PostMapping(value = "/undeleteuser/")
	public boolean unDeleteUser(@RequestBody User u) {
		
		
	    String url=mybundle.getString("DeleteUserController.undeleteuserurl");
	   

	  boolean status=resttemplate.postForObject(url,u,Boolean.class);
	  
	  return status;
	    
	    
	   
		
	
	}
	@CrossOrigin(origins="*")
	
	@PostMapping(value = "/purgeuser/")
	public boolean purgeUser(@RequestBody User u)  {
		
	
	    String url=mybundle.getString("DeleteUserController.purgeuserurl");
	   
	  boolean status=resttemplate.postForObject(url,u,Boolean.class);
	 
	  return status;
	    
	    
	   
		
	
	}
}

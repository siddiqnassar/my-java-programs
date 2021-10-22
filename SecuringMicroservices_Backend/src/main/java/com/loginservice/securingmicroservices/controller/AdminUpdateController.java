package com.loginservice.securingmicroservices.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin
public class AdminUpdateController {

	
	ResourceBundle mybundle = ResourceBundle.getBundle("adminupdatecontroller");

	@Autowired
	private RestTemplate resttemplate;

	@SuppressWarnings("rawtypes")
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/api/getAll")
	public ResponseEntity<List> getAllUser(HttpServletRequest request) {
		String url = mybundle.getString("AdminUpdateCobtroller.getallusersurl");
		
     	return  resttemplate.getForEntity(url, List.class);
	}

	@SuppressWarnings("rawtypes")
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getAllActiveUsers")
	public ResponseEntity<List> getAllActiveUsers(HttpServletRequest request){

	
		String url =  mybundle.getString("AdminUpdateCobtroller.getallactiveuserurl");

		
		return  resttemplate.getForEntity(url, List.class);

	}
}
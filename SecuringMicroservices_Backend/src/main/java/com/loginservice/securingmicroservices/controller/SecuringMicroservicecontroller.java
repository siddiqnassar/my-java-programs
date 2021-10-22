package com.loginservice.securingmicroservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController("/")
public class SecuringMicroservicecontroller {
	
		Logger log = LoggerFactory.getLogger(SecuringMicroservicecontroller.class);
	
		@Autowired
		private RestTemplate restTemplate;
	
		@CrossOrigin(origins = "http://localhost:8010", allowedHeaders = "*")
		@GetMapping("/greet")
		public String greet(){
			return "hello world";
		}
		
		@CrossOrigin(origins = "http://localhost:8010", allowedHeaders = "*")
		@GetMapping("/reviews")
		public String greet1(){
			ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8989/user/getreviews/1", String.class);
			log.info(response.getBody());
		
			return response.getBody();
		}
}

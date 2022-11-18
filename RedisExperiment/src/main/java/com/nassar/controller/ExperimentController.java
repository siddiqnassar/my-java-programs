package com.nassar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("get")
public class ExperimentController {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${pong.service.url}")
	private String pongURL;
	
	//public static String URL = "http://host.docker.internal:8092/get/result/";
	
	 Logger logger = LoggerFactory.getLogger(ExperimentController.class);
	 
	@GetMapping("/result/{name}")
	public ResponseEntity<String> getResult (@PathVariable("name") String name) {
		//return new ResponseEntity<>("Hello Nassar you are in Docker",HttpStatus.OK);
		try {
			 logger.info("URL is {} ", pongURL+name);;
			return new ResponseEntity<>(restTemplate.getForObject(pongURL +"/get/result/"+name, String.class),HttpStatus.OK);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
		}
	}
}

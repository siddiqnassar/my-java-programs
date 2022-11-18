package com.nassar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nassar.compositepattern.FileSystemService;

@RestController
@RequestMapping("get")
public class ExperimentController {

	@Autowired
	FileSystemService fileService;
	@GetMapping("/result/{name}")
	public ResponseEntity<String> getResult (@PathVariable("name") String name) {
		//return new ResponseEntity<>("Hello Nassar you are in Docker",HttpStatus.OK);
	    return ResponseEntity.status(HttpStatus.OK)
	            .body("Hello " + name + " you are Running Spring Boot app in Docker Container");
		
	}
	
	@GetMapping("/compositepattern")
	public ResponseEntity<String> getFileSystem () {
	    return ResponseEntity.status(HttpStatus.OK)
	            .body(fileService.printFileSystem());
		
	}
	
}

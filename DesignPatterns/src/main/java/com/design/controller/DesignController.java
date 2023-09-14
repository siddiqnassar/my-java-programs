package com.design.controller;

import com.design.models.Computer;
import com.design.service.ProtoTypeService;
import com.design.service.SingleTonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/get")
@CrossOrigin(origins = "http://localhost:3000")
public class DesignController {


	@Autowired
	SingleTonService singleTonService1;
   
	@Autowired
	SingleTonService singleTonService2;
	
	@Autowired
	ProtoTypeService protoType1;
	
	@Autowired
	ProtoTypeService protoType2;
	
   @GetMapping(value = "/singleton")
   public ResponseEntity<Boolean> singleTon() {
		//By default autowired returns singleton as design service is singleton and so below should return true as both are same
       return ResponseEntity.status(HttpStatus.OK).body(singleTonService1==singleTonService2);
   }
   @GetMapping(value = "/prototype")
   public ResponseEntity<Boolean> protoType() {
		//The below should return false as both are not same and completely diferent objects
       return ResponseEntity.status(HttpStatus.OK).body(protoType1==protoType2);
   }
   
   @GetMapping(value ="/builder")
   public ResponseEntity<String> builderPattern() {
	   //We will set in Computer main class and add that in computer Builder
	   Computer.ComputerBuilder cb = new Computer.ComputerBuilder();
	   cb.addCores(1).addRam("1GB");
	   Computer computer = cb.builder();
	   return ResponseEntity.status(HttpStatus.OK).body(computer.getRam());
   }
}

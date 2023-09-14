package com.design.experimentController;

import com.design.experimentService.ExperimentService;
import com.design.solidprinciples.SolidprinciplesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/experiment")
@CrossOrigin(origins = "http://localhost:3000")
public class ExperimentController {

	@Autowired
	ExperimentService experimentService;
	
	@Autowired
	SolidprinciplesService solidPrinciples;
   

   @GetMapping(value = "/lambda")
   public ResponseEntity<String> lambdaExpression() {
       return ResponseEntity.status(HttpStatus.OK).body(experimentService.lambaExpression());
   }

    @RequestMapping(value = "/custom/{id}", method = RequestMethod.GET)
    public String getRequestSwagger(@PathVariable("id") int id) {
        return "Id is " + id;
    }
   @GetMapping(value = "/stream")
   public ResponseEntity<List<Integer>> streamManipulation() {
       return ResponseEntity.status(HttpStatus.OK).body(experimentService.streamManipulation());
   }
   
   @GetMapping(value = "/solid")
   public ResponseEntity<String> solidPrinciples() {
       return ResponseEntity.status(HttpStatus.OK).body(solidPrinciples.solid());
   }
}

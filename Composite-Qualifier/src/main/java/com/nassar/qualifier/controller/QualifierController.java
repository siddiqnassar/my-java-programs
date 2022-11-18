package com.nassar.qualifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nassar.qualifier.service.QualifierService;

@RestController
@RequestMapping("qualifier")
public class QualifierController {

	@Autowired
	QualifierService qualifierService;
	@GetMapping("/get")
	public String getQualifier() {
		
		return qualifierService.testQualifier();
	}
}

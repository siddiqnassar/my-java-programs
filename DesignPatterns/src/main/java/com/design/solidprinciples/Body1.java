package com.design.solidprinciples;

import org.springframework.stereotype.Service;

@Service
public class Body1 implements HumanBody{

	@Override
	public String heart() {
		return "I am from Body 1";
	}
	
}

package com.nassar.qualifier.service;

import org.springframework.stereotype.Service;

@Service("HumanBody1")
public class HumanBody1 implements Heart{

	@Override
	public String pump() {
		return this.getClass().toString();
		
	}

}

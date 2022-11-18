package com.nassar.qualifier.service;

import org.springframework.stereotype.Service;

@Service("HumanBody2")
public class HumanBody2 implements Heart{


	@Override
	public String pump() {
		return this.getClass().toString();
	}

}
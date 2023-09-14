package com.design.solidprinciples;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class SolidprinciplesService {

	
	public String solid() {
//		ArrayList<Integer> list = new ArrayList<>();
//		list.add(2);
//		list.add(5);
//        list.add(10);
//	    list.add(6);
//	    list.add(101);
//		list.add(8);
//		list.add(0);
//		list.add(8);
//		list.add(31);
//		
//		return "nassar in solid";
		
		HumanBody body1 = new Body1();
		HumanBody body2 = new Body2();
		return body2.heart();
	}
}

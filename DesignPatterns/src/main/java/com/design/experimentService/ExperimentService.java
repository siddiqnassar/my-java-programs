package com.design.experimentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ExperimentService {

	public String lambaExpression() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(5);
        list.add(10);
	    list.add(6);
	    list.add(101);
		list.add(8);
		list.add(0);
		list.add(8);
		list.add(31);
		Boolean isPresent = list.isEmpty();
		
		list.forEach((item) -> {
			System.out.println(item);
			
		});
		
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		return "nassar in lamda";
	}
	
	public List<Integer> streamManipulation() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(5);
        list.add(10);
	    list.add(6);
	    list.add(101);
		list.add(8);
		list.add(0);
		list.add(8);
		list.add(31);
		
		//take list greater than 5
		List<Integer> list1 = list.stream().filter(x -> x > 5).collect(Collectors.toList());
		
		//take the squares of all the numbers
		List<Integer> list2 = list.stream().map(x -> x * x).collect(Collectors.toList());
		
		//Usage of foreach with addition of 1 and then printing
		list.stream().map(x -> x + 1).forEach(x -> System.out.println(x));
		
		//usage of reduce 
		//argument is default or start initilization, 
		int x = list.stream().reduce(0,(x1,x2)-> x1+x2);
		System.out.println("x is " + x);
		
		return list2;
	}
}

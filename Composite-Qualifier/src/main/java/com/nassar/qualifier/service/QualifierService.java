package com.nassar.qualifier.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class QualifierService {

	@Autowired
	@Qualifier("HumanBody2")
	private Heart heart;
	public String testQualifier() {
	    System.out.println("Hii before Async Methods");
		CompletableFuture.runAsync(() -> {
		    System.out.println("Hii in Async Methods");
		});
	    System.out.println("Hii after Async Methods");
		return heart.pump();
	}
}

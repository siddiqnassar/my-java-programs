package com.locationsimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class Location_Simulator {

	public static void main(String[] args) {
		SpringApplication.run(Location_Simulator.class, args);
	}


	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}

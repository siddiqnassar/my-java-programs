package com.nassar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NassarExperimentApplication {

	public static void main(String[] args) {
		SpringApplication.run(NassarExperimentApplication.class, args);
		
	}

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}

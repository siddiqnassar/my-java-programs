package com.loginportal.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@EnableCaching
public class DataServiceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DataServiceApplication.class, args);
	}
}

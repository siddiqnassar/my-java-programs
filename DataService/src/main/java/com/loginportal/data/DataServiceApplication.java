package com.loginportal.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableEurekaClient
//@EnableCaching
public class DataServiceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DataServiceApplication.class, args);
	}
}

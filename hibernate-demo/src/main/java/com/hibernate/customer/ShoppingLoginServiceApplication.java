package com.hibernate.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class ShoppingLoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingLoginServiceApplication.class, args);
	}

}

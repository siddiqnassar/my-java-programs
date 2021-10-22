package com.loginportal.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.loginportal.data.cipher.DateEncryptDecryptConverter;
import com.loginportal.data.cipher.StringEncryptDecryptConverter;

@Configuration
public class AppConfig {
	@Bean
	public StringEncryptDecryptConverter getStringEncryptDecryptConverter() {
		return new StringEncryptDecryptConverter();
	}

	@Bean
	public DateEncryptDecryptConverter getDateEncryptDecryptConverter() {
		return new DateEncryptDecryptConverter();
	}
}

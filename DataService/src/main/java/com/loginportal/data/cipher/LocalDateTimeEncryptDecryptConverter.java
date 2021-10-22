package com.loginportal.data.cipher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeEncryptDecryptConverter extends AbstractEncryptDecryptConverter<LocalDateTime> {

	@Override
	LocalDateTime convertStringToEntityAttribute(String dbData) {
		if (dbData != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			System.out.println("########### " + LocalDateTime.parse(dbData, formatter));
			return LocalDateTime.parse(dbData, formatter);
		} else {
			return null;
		}
	}

	@Override
	String convertEntityAttributeToString(LocalDateTime localDateTime) {
		if (localDateTime != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			System.out.println("######## " + localDateTime.format(formatter));
			return localDateTime.format(formatter);
		} else {
			return null;
		}
	}
}

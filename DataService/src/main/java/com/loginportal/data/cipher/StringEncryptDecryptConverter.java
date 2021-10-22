package com.loginportal.data.cipher;

import javax.persistence.Converter;

@Converter
public class StringEncryptDecryptConverter extends AbstractEncryptDecryptConverter<String> {

	@Override
	String convertStringToEntityAttribute(String dbData) {
		return dbData;
	}

	@Override
	String convertEntityAttributeToString(String attribute) {
		return attribute;
	}
}

package com.loginportal.data.cipher;

public class BooleanEncryptDecryptConverter extends AbstractEncryptDecryptConverter<Boolean> {

	@Override
	Boolean convertStringToEntityAttribute(String dbData) {
		if (dbData != null)
			return Boolean.parseBoolean(dbData);
		else
			return null;
	}

	@Override
	String convertEntityAttributeToString(Boolean booleanValue) {
		if (booleanValue != null)
			return String.valueOf(booleanValue);
		else
			return null;
	}
}

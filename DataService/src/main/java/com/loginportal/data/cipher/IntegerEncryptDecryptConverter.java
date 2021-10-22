package com.loginportal.data.cipher;

public class IntegerEncryptDecryptConverter extends AbstractEncryptDecryptConverter<Integer> {

	@Override
	Integer convertStringToEntityAttribute(String dbData) {
		if (dbData != null)
			return Integer.parseInt(dbData);
		else
			return null;
	}

	@Override
	String convertEntityAttributeToString(Integer integer) {
		if (integer != null)
			return String.valueOf(integer);
		else
			return null;
	}
}

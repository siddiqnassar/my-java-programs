package com.loginportal.data.cipher;

import javax.persistence.AttributeConverter;

import org.apache.tomcat.util.codec.binary.Base64;

public abstract class AbstractEncryptDecryptConverter<X> implements AttributeConverter<X, String> {

	private static final String KEY = CipherUtility.getKeyFromConf();
	private static final String NONCE = CipherUtility.getNonceFromConf();

	CipherUtility cipherUtility = new CipherUtility();

	@Override
	public String convertToDatabaseColumn(X attribute) {
		if (attribute != null) {
			try {
				byte[] keyBytes = Base64.decodeBase64(KEY);
				byte[] nonceBytes = Base64.decodeBase64(NONCE);
				String attributeString = convertEntityAttributeToString(attribute);
				byte[] attributeBytes = attributeString.getBytes();
				String encryptedString = "";
				encryptedString = cipherUtility.encrypt(attributeBytes, keyBytes, nonceBytes);
				return encryptedString;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return convertEntityAttributeToString(attribute);
	}

	@Override
	public X convertToEntityAttribute(String dbData) {
		if (dbData != null && !dbData.equals("")) {
			try {
				byte[] keyBytes = Base64.decodeBase64(KEY);
				byte[] nonceBytes = Base64.decodeBase64(NONCE);
				byte[] dbdataBytes = Base64.decodeBase64(dbData);
				String decryptedString = "";
				decryptedString = cipherUtility.decrypt(dbdataBytes, keyBytes, nonceBytes);
				return convertStringToEntityAttribute(decryptedString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return convertStringToEntityAttribute(dbData);
	}

	abstract X convertStringToEntityAttribute(String dbData);

	abstract String convertEntityAttributeToString(X attribute);
}

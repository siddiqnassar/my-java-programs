package com.loginportal.data.cipher;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class CipherUtility {
	private static ResourceBundle mybundle = ResourceBundle.getBundle("database-security");

	public SecretKey generateKeyAsSecretKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		return keyGenerator.generateKey();
	}

	public byte[] generateKeyAsBytes() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		SecretKey key = keyGenerator.generateKey();
		return key.getEncoded();
	}

	public String generateKeyAsString() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		SecretKey key = keyGenerator.generateKey();
		return Base64.encodeBase64String(key.getEncoded());
	}

	public byte[] generateIVAsBytes() {
		byte[] IV = new byte[16];
		SecureRandom random = new SecureRandom();
		random.nextBytes(IV);
		return IV;
	}

	public String generateIVAsString() {
		byte[] IV = new byte[16];
		SecureRandom random = new SecureRandom();
		random.nextBytes(IV);
		return Base64.encodeBase64String(IV);
	}

	public String encrypt(byte[] plaintext, byte[] key, byte[] IV) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
//		SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(IV);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		return Base64.encodeBase64String(cipher.doFinal(plaintext));
	}

	public String decrypt(byte[] cipherText, byte[] key, byte[] IV) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(IV);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		return new String(cipher.doFinal(cipherText));
	}

	public static String getKeyFromConf() {
		return mybundle.getString("Cipher.key");
	}

	public static String getNonceFromConf() {
		return mybundle.getString("Cipher.nonce");
	}
}

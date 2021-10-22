package com.loginportal.data.mongoconverter;

import java.util.Date;

import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

import com.loginportal.data.cipher.DateEncryptDecryptConverter;
import com.loginportal.data.cipher.StringEncryptDecryptConverter;
import com.loginportal.data.model.ArchiveUser;

public class ArchiveUserWriteConverter implements Converter<ArchiveUser, Document> {

	StringEncryptDecryptConverter stringConverter = new StringEncryptDecryptConverter();
	DateEncryptDecryptConverter dateConverter = new DateEncryptDecryptConverter();

	public String encrypt(String attribute) {
		return stringConverter.convertToDatabaseColumn(attribute);
	}

	public String encrypt(Date attribute) {
		return dateConverter.convertToDatabaseColumn(attribute);
	}

	@Override
	public Document convert(final ArchiveUser archiveUser) {
		System.out.println("$ ArchiveUserWriteConverter $");

		Document document = new Document();
		document.put("userID", archiveUser.getUserID());

		document.put("emailID", encrypt(archiveUser.getEmailID()));
		document.put("userRole", encrypt(archiveUser.getUserRole()));
		document.put("firstName", encrypt(archiveUser.getFirstName()));
		document.put("lastName", encrypt(archiveUser.getLastName()));

		document.put("phoneNo", encrypt(archiveUser.getPhoneNo()));
		document.put("gender", encrypt(archiveUser.getGender()));
		document.put("maritalStatus", encrypt(archiveUser.getMaritalStatus()));
		document.put("profession", encrypt(archiveUser.getProfession()));

		document.put("accountCreationTime", encrypt(archiveUser.getAccountCreationTime()));
		document.put("dateOfBirth", encrypt(archiveUser.getDateOfBirth()));

		return document;
	}
}

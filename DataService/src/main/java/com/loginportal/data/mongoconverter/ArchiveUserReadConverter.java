package com.loginportal.data.mongoconverter;

import java.util.Date;

import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

import com.loginportal.data.cipher.DateEncryptDecryptConverter;
import com.loginportal.data.cipher.StringEncryptDecryptConverter;
import com.loginportal.data.model.ArchiveUser;

public class ArchiveUserReadConverter implements Converter<Document, ArchiveUser> {

	StringEncryptDecryptConverter stringConverter = new StringEncryptDecryptConverter();
	DateEncryptDecryptConverter dateConverter = new DateEncryptDecryptConverter();

	public String decryptAsString(String field) {
		return stringConverter.convertToEntityAttribute(field);
	}

	public Date decryptAsDate(String field) {
		return dateConverter.convertToEntityAttribute(field);
	}

	@Override
	public ArchiveUser convert(final Document document) {
		System.out.println("$ ArchiveUsersReadConverter $");

		ArchiveUser archiveUser = new ArchiveUser();
		archiveUser.setUserID((Long) document.get("userID"));

		archiveUser.setEmailID(decryptAsString((String) document.get("emailID")));
		archiveUser.setUserRole(decryptAsString((String) document.get("userRole")));
		archiveUser.setFirstName(decryptAsString((String) document.get("firstName")));
		archiveUser.setLastName(decryptAsString((String) document.get("lastName")));

		archiveUser.setPhoneNo(decryptAsString((String) document.get("phoneNo")));
		archiveUser.setGender(decryptAsString((String) document.get("gender")));
		archiveUser.setMaritalStatus(decryptAsString((String) document.get("maritalStatus")));
		archiveUser.setProfession(decryptAsString((String) document.get("profession")));

		archiveUser.setAccountCreationTime(decryptAsDate((String) document.get("accountCreationTime")));
		archiveUser.setDateOfBirth(decryptAsDate((String) document.get("dateOfBirth")));

		return archiveUser;
	}
}

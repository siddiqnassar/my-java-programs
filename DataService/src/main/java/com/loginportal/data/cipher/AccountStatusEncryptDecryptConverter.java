package com.loginportal.data.cipher;

import javax.persistence.Converter;

import com.loginportal.data.model.AccountStatus;

@Converter
public class AccountStatusEncryptDecryptConverter extends AbstractEncryptDecryptConverter<AccountStatus> {

	@Override
	AccountStatus convertStringToEntityAttribute(String dbData) {
		if (dbData != null)
			return AccountStatus.valueOf(dbData);
		else
			return null;
	}

	@Override
	String convertEntityAttributeToString(AccountStatus accountStatus) {
		if (accountStatus != null)
			return accountStatus.toString();
		else
			return null;
	}
}

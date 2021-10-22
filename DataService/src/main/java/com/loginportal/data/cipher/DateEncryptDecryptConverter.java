package com.loginportal.data.cipher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEncryptDecryptConverter extends AbstractEncryptDecryptConverter<Date> {

	@Override
	Date convertStringToEntityAttribute(String dbData) {
		if (dbData != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
			Date date = new Date();
			try {
				date = dateFormat.parse(dbData);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
		} else {
			return null;
		}
	}

	@Override
	String convertEntityAttributeToString(Date date) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
			return dateFormat.format(date);
		}
		return null;
	}
}

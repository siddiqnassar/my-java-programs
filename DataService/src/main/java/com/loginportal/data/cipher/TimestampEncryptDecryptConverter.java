package com.loginportal.data.cipher;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Converter;

@Converter
public class TimestampEncryptDecryptConverter extends AbstractEncryptDecryptConverter<Timestamp> {

	@Override
	Timestamp convertStringToEntityAttribute(String dbData) {
		if (dbData != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
			Date parsedTimeStamp = new Date();
			try {
				parsedTimeStamp = dateFormat.parse(dbData);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Timestamp(parsedTimeStamp.getTime());
		} else {
			return null;
		}
	}

	@Override
	String convertEntityAttributeToString(Timestamp timestamp) {
		if (timestamp != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
			return dateFormat.format(timestamp);
		} else {
			return null;
		}
	}
}

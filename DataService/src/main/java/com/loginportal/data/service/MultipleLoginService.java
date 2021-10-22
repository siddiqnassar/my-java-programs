package com.loginportal.data.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.loginportal.data.model.MultipleLogin;
import com.loginportal.data.repository.MultipleLoginRepository;

@Service
public class MultipleLoginService {
	@Autowired
	MultipleLoginRepository multipleLoginRepository;
	
	@Transactional
//	@Cacheable(value = "MultipleLogin", key = "#multipleLogin.ipAddress + #multipleLogin.userID", unless = "#result == null")
	public Optional<MultipleLogin> getMultipleLoginByIPAndUserID(MultipleLogin multipleLogin) {
		return multipleLoginRepository.getMultipleLoginByIPAndUserID(multipleLogin.getIpAddress(), multipleLogin.getUserID());
	}
	
	@Transactional
	public MultipleLogin createMultipleLogin(MultipleLogin multipleLogin) {
		Timestamp timestamp = new Timestamp((new Date()).getTime());
		multipleLogin.setLoggedInTime(timestamp);
		return multipleLoginRepository.save(multipleLogin);
	}
}

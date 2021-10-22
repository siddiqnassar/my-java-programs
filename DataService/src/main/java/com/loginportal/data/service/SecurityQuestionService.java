package com.loginportal.data.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.loginportal.data.model.SecurityQuestion;
import com.loginportal.data.repository.SecurityQuestionRepository;

@Service
public class SecurityQuestionService {

	@Autowired
	SecurityQuestionRepository securityQuestionRepository;

	@Transactional
	@Cacheable(value = "SecurityQuestion", key = "secQ")
	public List<SecurityQuestion> getSecurityQuestions() {
		return securityQuestionRepository.findAll();
	}

	@Transactional
	public SecurityQuestion createSecurityQuestion(SecurityQuestion securityQuestion) {
		return securityQuestionRepository.save(securityQuestion);
	}
}

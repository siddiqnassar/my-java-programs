package com.loginservice.securingmicroservices.controller;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.loginservice.securingmicroservices.config.JWTtokenUtil;
import com.loginservice.securingmicroservices.model.JwtRequest;
import com.loginservice.securingmicroservices.model.OTPRequest;
import com.loginservice.securingmicroservices.model.User;

@RestController
public class ChangePasswordController {
	Logger log = LoggerFactory.getLogger(SecuringMicroservicecontroller.class);
	String auth = "Authorization";
	ResourceBundle mybundle = ResourceBundle.getBundle("changepasswordcontroller");

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private JWTtokenUtil jwttokenutil;

	@CrossOrigin("*")
	@PostMapping("/sendotp1")
	public String sendOtp(HttpServletRequest request, @RequestBody User u) {
		String tokens = request.getHeader(auth);
		String token = tokens.substring(7);
		String id = jwttokenutil.getUserIDFromToken(token);
		Long userID = Long.parseLong(id);

		u.setUserID(userID);

		
		return restTemplate.postForObject(mybundle.getString("ChangePassowrdController.sendotpurl"), u, String.class);
	}

	@CrossOrigin("*")
	@PostMapping("/validateotp1")
	public String validateOtp(HttpServletRequest request, @RequestBody OTPRequest otpRequest) {
		String tokens = request.getHeader(auth);
		String token = tokens.substring(7);
		String id = jwttokenutil.getUserIDFromToken(token);
		Long userID = Long.parseLong(id);

		otpRequest.setUserID(userID);

		return restTemplate.postForObject(mybundle.getString("ChangePassowrdController.validateotpurl"), otpRequest, String.class);
	}

	@CrossOrigin("*")
	@PostMapping("/change")
	public String changePassword(HttpServletRequest request, @RequestBody JwtRequest otpRequest) {
		String tokens = request.getHeader(auth);
		String token = tokens.substring(7);
		String id = jwttokenutil.getUserIDFromToken(token);
		Long userID = Long.parseLong(id);
System.out.println(otpRequest);
		otpRequest.setUserID(userID);
		
		ResponseEntity<String> response = restTemplate.postForEntity(mybundle.getString("ChangePassowrdController.changepasswordurl"), otpRequest,
				String.class);
		log.info(response.getBody());
	
		return response.getBody();
	}

}
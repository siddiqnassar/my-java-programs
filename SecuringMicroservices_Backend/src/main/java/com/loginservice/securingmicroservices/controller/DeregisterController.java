package com.loginservice.securingmicroservices.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.loginservice.securingmicroservices.config.JWTtokenUtil;
import com.loginservice.securingmicroservices.model.JwtRequest;
import com.loginservice.securingmicroservices.model.Token;

@RestController
public class DeregisterController {
	Logger log = LoggerFactory.getLogger(SecuringMicroservicecontroller.class);
	String res = "response {}";
	Token token=new Token();
	private RestTemplate restTemplate=new RestTemplate();
	@Autowired
	private JWTtokenUtil jwttokenutil;
	ResourceBundle mybundle = ResourceBundle.getBundle("deregistercontroller");

	@CrossOrigin("*")
	@PostMapping("/deactivate")
	public ObjectNode deActivate(@RequestBody JwtRequest request, HttpServletRequest htp) {

		String token = htp.getHeader("Authorization").substring(7);
		log.info(token);
		log.info(jwttokenutil.getUserIDFromToken(token));

		String id = jwttokenutil.getUserIDFromToken(token);
		Long userID = Long.parseLong(id);
		request.setUserID(userID);
       
		ObjectNode response = restTemplate.postForObject(mybundle.getString("DeregisterController.deactivateurl"), request,
				ObjectNode.class);

		log.info("response1 {}", response);
		return response;
	}

	@CrossOrigin("*")
	@PostMapping("/forgetuser")
	public ObjectNode forgetMe(@RequestBody JwtRequest request, HttpServletRequest htp) {

		String token = htp.getHeader("Authorization").substring(7);
		log.info(token);
		log.info(jwttokenutil.getUserIDFromToken(token));

		String id = jwttokenutil.getUserIDFromToken(token);
		Long userID = Long.parseLong(id);
		request.setUserID(userID);

		ObjectNode response = restTemplate.postForObject(mybundle.getString("DeregisterController.forgetuserurl"), request,
				ObjectNode.class);

		log.info(res, response);
		return response;
	}

	@SuppressWarnings("rawtypes")
	@CrossOrigin("*")
	@GetMapping("/getreviews")
	public List getReviews() {

		return restTemplate.getForObject(mybundle.getString("DeregisterController.getreviewsurl"), List.class);

		
		
	}

}
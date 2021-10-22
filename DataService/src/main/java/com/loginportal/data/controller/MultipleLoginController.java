package com.loginportal.data.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginportal.data.model.MultipleLogin;
import com.loginportal.data.service.MultipleLoginService;

@RestController
@RequestMapping("/api/data/multiple-login")
public class MultipleLoginController {
	@Autowired
	MultipleLoginService multipleLoginService;

	@PostMapping("/find")
	public Optional<MultipleLogin> getUser(@RequestBody MultipleLogin multipleLogin) {
		return multipleLoginService.getMultipleLoginByIPAndUserID(multipleLogin);
	}

	@PostMapping("/create")
	public MultipleLogin createMultipleLogin(@RequestBody MultipleLogin multipleLogin) {
		return multipleLoginService.createMultipleLogin(multipleLogin);
	}
}

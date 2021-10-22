package com.loginservice.securingmicroservices.model;

import lombok.Data;

@Data
public class LoginStatus {
	private Long userID;
	private String loginStatusMessage;

}

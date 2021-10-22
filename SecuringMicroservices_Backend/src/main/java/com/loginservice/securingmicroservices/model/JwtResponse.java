package com.loginservice.securingmicroservices.model;
import java.io.Serializable;

import lombok.Data;
@Data
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private  Token jwttoken;
	private  String token;
	private LoginStatus loginStatus;
	public JwtResponse(String token)
	{
		this.token=token;
	}
	
	public JwtResponse(Token jwttoken,LoginStatus loginStatusMessage) {
		this.jwttoken = jwttoken;
		this.loginStatus=loginStatusMessage;
	}

	
	
}
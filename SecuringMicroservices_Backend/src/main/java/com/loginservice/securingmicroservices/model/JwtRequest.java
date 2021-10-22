package com.loginservice.securingmicroservices.model;

import java.io.Serializable;

import lombok.Data;


@Data
public class JwtRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	private Long userID;
	private String ans1;
	private String pwd;
	private String ans2;
	private String emailID;
	private String password;
	private PasswordHistory passwordHistory;
	private MultipleLogin multipleLogin;
    private String role;
    private int choice;
    private int otp;
	public void setMultipleLogin(MultipleLogin multipleLogin) {
		this.multipleLogin = multipleLogin;
	}

	public MultipleLogin getMultipleLogin() {
		return multipleLogin;
	}
	public JwtRequest()
	{
		//need default constructor for JSON Parsing	
	}
	
	public String getEmailID()
	{
		return this.emailID;
	}
	public void setEmailID(String emailID)
	{
		this.emailID=emailID;
	}
	
}

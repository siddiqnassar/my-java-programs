package com.loginservice.securingmicroservices.model;

import lombok.Data;

@Data
public class Token {
private String jwtToken;
private String refreshToken;
}

/*
 * package com.loginservice.securingmicroservices.controller;
 * 
 * 
 * 
 * import static org.junit.Assert.*;
 * 
 * 
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.Mockito;
 * import org.springframework.web.client.RestTemplate;
 * 
 * 
 * 
 * import com.loginservice.securingmicroservices.config.JWTtokenUtil; import
 * com.loginservice.securingmicroservices.model.JwtRequest; import
 * com.loginservice.securingmicroservices.model.JwtResponse; import
 * com.loginservice.securingmicroservices.model.LoginStatus; import
 * com.loginservice.securingmicroservices.model.OTPRequest; import
 * com.loginservice.securingmicroservices.model.Token;
 * 
 * import org.springframework.http.*;
 * 
 * 
 * 
 * public class LoginServiceControllerTest extends AbstractTestClass{
 * 
 * @Mock private RestTemplate restTemplate;
 * 
 * @Mock private JWTtokenUtil jwtTokenUtil;
 * 
 * @InjectMocks private LoginServiceController lgc;
 * 
 * private JwtRequest authenticationRequest;
 * 
 * private OTPRequest otpRequest;
 * 
 * @Before public void setUp() { otpRequest = new OTPRequest();
 * otpRequest.setUserID((long) 2); authenticationRequest = new JwtRequest();
 * authenticationRequest.setEmailID("shaikneha823@gmail.com"); super.setUp(); }
 * 
 * 
 * 
 * @Test public void testCreateAuthenticationToken() throws Exception { String
 * url = "http://localhost:8013/api/login"; LoginStatus return_object = new
 * LoginStatus(); return_object.setLoginStatusMessage("Authenticated");
 * return_object.setUserID((long) 2);
 * Mockito.when(restTemplate.postForObject(url, authenticationRequest,
 * LoginStatus.class)).thenReturn(return_object); String token =
 * jwtTokenUtil.generateToken(authenticationRequest,
 * return_object.getUserID(),0); String
 * refreshtoken=jwtTokenUtil.generateRefreshToken(); Token tokens=new Token();
 * tokens.setJwtToken(token); tokens.setRefreshToken(refreshtoken);
 * System.out.println(token); System.out.println(ResponseEntity.ok(new
 * JwtResponse(tokens, return_object)).getBody().getClass()); ResponseEntity<?>
 * expected = lgc.createAuthenticationToken(authenticationRequest);
 * ResponseEntity<?> actual = ResponseEntity.ok(new JwtResponse(tokens,
 * return_object));
 * assertEquals(actual.getStatusCode(),expected.getStatusCode());
 * assertEquals(((JwtResponse)
 * actual.getBody()).getLoginStatus().getLoginStatusMessage(),((JwtResponse)
 * expected.getBody()).getLoginStatus().getLoginStatusMessage()); }
 * 
 * 
 * 
 * @Test public void testSendOtpEmail() { LoginStatus return_object = new
 * LoginStatus(); return_object.setLoginStatusMessage("Authenticated");
 * return_object.setUserID((long) 2); String url =
 * "http://localhost:8013/api/sendOtpEmail";
 * Mockito.when(restTemplate.postForObject(url,authenticationRequest,
 * LoginStatus.class)).thenReturn(return_object); LoginStatus expected =
 * lgc.sendOtpEmail(authenticationRequest); assertEquals(expected,
 * return_object);
 * 
 * }
 * 
 * 
 * 
 * 
 * 
 * @Test public void testVerifyOtp() { String url =
 * "http://localhost:8013/api/validateOtp";
 * Mockito.when(restTemplate.postForObject(url, otpRequest,
 * Boolean.class)).thenReturn(true); ResponseEntity<?> expected =
 * lgc.verifyOtp(otpRequest);
 * assertEquals(((JwtResponse)expected.getBody()).getLoginStatus().
 * getLoginStatusMessage(),"true"); }
 * 
 * 
 * 
 * @Test public void testVerifyOtpEmail() { String url =
 * "http://localhost:8013/api/validateOtpEmail";
 * Mockito.when(restTemplate.postForObject(url, otpRequest,
 * Boolean.class)).thenReturn(true); ResponseEntity<?> expected =
 * lgc.verifyOtpEmail(otpRequest);
 * assertEquals(expected.getStatusCode(),org.springframework.http.HttpStatus.OK)
 * ; assertEquals(((JwtResponse)expected.getBody()).getLoginStatus().
 * getLoginStatusMessage(),"true"); Mockito.when(restTemplate.postForObject(url,
 * otpRequest, Boolean.class)).thenReturn(false); expected =
 * lgc.verifyOtpEmail(otpRequest);
 * assertEquals(expected.getStatusCode(),org.springframework.http.HttpStatus.OK)
 * ; assertEquals(((JwtResponse)expected.getBody()).getLoginStatus().
 * getLoginStatusMessage(),"false"); }
 * 
 * 
 * 
 * }
 */
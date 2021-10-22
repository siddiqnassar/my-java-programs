//package com.loginservice.securingmicroservices.controller;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import org.springframework.web.client.RestTemplate;
//
//import com.loginservice.securingmicroservices.config.JWTtokenUtil;
//import com.loginservice.securingmicroservices.controller.DeleteUserServiceContoller;
//import com.loginservice.securingmicroservices.model.JwtRequest;
//import com.loginservice.securingmicroservices.model.User;
//
//public class DeleteUserServiceContollerTest extends AbstractTestClass {
//
//	@Mock
//	private RestTemplate restTemplate;
//	private JwtRequest authenticationRequest;
//	@InjectMocks
//	private DeleteUserServiceContoller lgc;
//	@Mock
//	private JWTtokenUtil jwtTokenUtil;
//	private User user;
//	 @Before 
//	  public void setUp() {
//		 user=new User();
//		 authenticationRequest=new JwtRequest();
//		 user.setUserID(new Long(2));
//		 authenticationRequest.setUserID(new Long(2));
//		 authenticationRequest.setRole("user");
//		 authenticationRequest.setEmailID("shaikneha823@gmail.com");
//	  }
//	@Test
//	public void testDeleteUser() {
//		String url = "http://DeleteUser-Service/deleteuser/";
//		
//		  	
//			boolean status=true;
//			
//			Mockito.when(restTemplate.postForObject(url,user,Boolean.class)).thenReturn(status);
//			
//			boolean expected = lgc.deleteUser(user);
//			boolean actual = status;
//			assertEquals(actual,expected);
//			
//		
//	}
//
//	@Test
//	public void testUnDeleteUser() {
//		String url = "http://DeleteUser-Service/undeleteuser/";
//		boolean status=true;
//		
//		Mockito.when(restTemplate.postForObject(url,user,Boolean.class)).thenReturn(status);
//		
//		
//		boolean expected = lgc.unDeleteUser(user);
//		boolean actual = status;
//		assertEquals(actual,expected);
//	}
//
//	@Test
//	public void testPurgeUser() {
//		
//		boolean status=true;
//		String url = "http://DeleteUser-Service/purgeuser/";
//		Mockito.when(restTemplate.postForObject(url,user,Boolean.class)).thenReturn(status);
//		
//		
//	
//		boolean expected = lgc.purgeUser(user);
//		boolean actual = status;
//		assertEquals(actual,expected);
//	}
//
//
//	
//
//}

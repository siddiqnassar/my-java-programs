package com.loginservice.securingmicroservices.controller;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.loginservice.securingmicroservices.WebSecurityConfig;
import com.loginservice.securingmicroservices.config.JWTtokenUtil;
import com.loginservice.securingmicroservices.model.JwtRequest;
import com.loginservice.securingmicroservices.model.JwtResponse;
import com.loginservice.securingmicroservices.model.LoginStatus;
import com.loginservice.securingmicroservices.model.OTPRequest;
import com.loginservice.securingmicroservices.model.Token;
import com.loginservice.securingmicroservices.model.User;

@RestController
@CrossOrigin
public class LoginServiceController {

	private Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, Long> valueOps;
	ResourceBundle mybundle = ResourceBundle.getBundle("logincontroller");

	@Autowired
	private JWTtokenUtil jwtTokenUtil;
	@Autowired
	private RestTemplate resttemplate;
	private String email;

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		String url = mybundle.getString("LoginController.loginurl");
		log.info("{}", authenticationRequest.getMultipleLogin());
		LoginStatus ls = resttemplate.postForObject(url, authenticationRequest, LoginStatus.class);
		System.out.println(ls);
		if (ls.getLoginStatusMessage().equals("Admin Authenticated")) {
			final String token = jwtTokenUtil.generateToken(authenticationRequest, ls.getUserID(),Integer.parseInt(mybundle.getString("LoginController.adminflag")));
			log.info(token);
			final String refreshToken=jwtTokenUtil.generateRefreshToken();
			Token tokens=new Token();
			tokens.setJwtToken(token);
			tokens.setRefreshToken(refreshToken);
			System.out.println(tokens);
			valueOps.set(refreshToken, ls.getUserID(), Integer.parseInt(mybundle.getString("LoginController.timelimit")), TimeUnit.MINUTES);
			log.info(jwtTokenUtil.getUserIDFromToken(token));
			System.out.println(new JwtResponse(tokens, ls));
			return ResponseEntity.ok(new JwtResponse(tokens, ls));
		} else if (ls.getLoginStatusMessage().equals("User Authenticated")) {
			final String token = jwtTokenUtil.generateToken(authenticationRequest, ls.getUserID(),Integer.parseInt(mybundle.getString("LoginController.userflag")));
			final String refreshToken=jwtTokenUtil.generateRefreshToken();
			Token tokens=new Token();
			tokens.setJwtToken(token);
			tokens.setRefreshToken(refreshToken);
			valueOps.set(refreshToken, ls.getUserID(), Integer.parseInt(mybundle.getString("LoginController.timelimit")), TimeUnit.MINUTES);
			log.info(token);
			log.info(jwtTokenUtil.getUserIDFromToken(token));
			return ResponseEntity.ok(new JwtResponse(tokens, ls));
		}
		else
		return ResponseEntity.ok(new JwtResponse(null, ls));
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/validateotp")
	public ResponseEntity<?> verifyOtp(@RequestBody OTPRequest otpRequest) {

		String url = mybundle.getString("LoginController.validateotpurl");
		System.out.println(otpRequest.getUserID());
		System.out.println( resttemplate.postForObject(url, otpRequest, Boolean.class));
		LoginStatus ls = new LoginStatus();
		ls.setUserID(otpRequest.getUserID());
		if (resttemplate.postForObject(url, otpRequest, Boolean.class)) {
			
			JwtRequest authenticationRequest = new JwtRequest();
			authenticationRequest.setUserID(otpRequest.getUserID());
			final String token = jwtTokenUtil.generateToken(authenticationRequest, authenticationRequest.getUserID(),Integer.parseInt(mybundle.getString("LoginController.userflag")));
			log.info(token);
			final String refreshToken=jwtTokenUtil.generateRefreshToken();
			Token tokens=new Token();
			tokens.setJwtToken(token);
			tokens.setRefreshToken(refreshToken);
			valueOps.set(refreshToken, ls.getUserID(), Integer.parseInt(mybundle.getString("LoginController.timelimit")), TimeUnit.MINUTES);
			log.info(jwtTokenUtil.getUserIDFromToken(token));
			ls.setLoginStatusMessage("true");
			return ResponseEntity.ok(new JwtResponse(tokens, ls));
		}
		ls.setLoginStatusMessage("false");
		return ResponseEntity.ok(new JwtResponse(null, ls));
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/sendotpemail")
	public LoginStatus sendOtpEmail(@RequestBody JwtRequest authenticationRequest) {

		String url = mybundle.getString("LoginController.sendotpemailurl");
		email = authenticationRequest.getEmailID();
		return resttemplate.postForObject(url, authenticationRequest, LoginStatus.class);
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/validateotpemail")
	public ResponseEntity<?> verifyOtpEmail(@RequestBody OTPRequest otpRequest) {

		String url = mybundle.getString("LoginController.validateotpemailurl");
		System.out.println(otpRequest.getUserID());
		LoginStatus ls = new LoginStatus();
		ls.setUserID(otpRequest.getUserID());
		if (resttemplate.postForObject(url, otpRequest, Boolean.class)) {
			JwtRequest authenticationRequest = new JwtRequest();
			authenticationRequest.setEmailID(email);
			authenticationRequest.setUserID(otpRequest.getUserID());
			authenticationRequest.setRole("user");
			System.out.println(authenticationRequest);
			final String token = jwtTokenUtil.generateToken(authenticationRequest, authenticationRequest.getUserID(),Integer.parseInt(mybundle.getString("LoginController.userflag")));
			log.info(token);
			final String refreshToken=jwtTokenUtil.generateRefreshToken();
			Token tokens=new Token();
			tokens.setJwtToken(token);
			tokens.setRefreshToken(refreshToken);
			valueOps.set(refreshToken, ls.getUserID(), 30, TimeUnit.MINUTES);
			log.info(jwtTokenUtil.getUserIDFromToken(token));
			ls.setLoginStatusMessage("true");
			return ResponseEntity.ok(new JwtResponse(tokens, ls));
		}
		ls.setLoginStatusMessage("false");
		return ResponseEntity.ok(new JwtResponse(null, ls));
	}
	@CrossOrigin(origins="*")
    @RequestMapping(value="/stay", method =RequestMethod.POST)
    public ResponseEntity<?> staySign(@RequestBody JwtRequest authenticationRequest)
    {
        String url="http://localhost:8013/api/login";
        System.out.println(authenticationRequest);
        User user=new User();
        user.setEmailID(authenticationRequest.getEmailID());
        user.setPasswordHistory(authenticationRequest.getPasswordHistory());
        LoginStatus ls=resttemplate.postForObject(url, user,LoginStatus.class);
        System.out.println(ls);
        if(ls.getLoginStatusMessage().contentEquals("User Authenticated"))
        {
            
            String token=jwtTokenUtil.generateToken(authenticationRequest,ls.getUserID(),Integer.parseInt(mybundle.getString("LoginController.staysignedflag")));
           
			authenticationRequest.setEmailID(email);
			authenticationRequest.setUserID(ls.getUserID());
			authenticationRequest.setRole("user");
			System.out.println(authenticationRequest);
			 token = jwtTokenUtil.generateToken(authenticationRequest, authenticationRequest.getUserID(),Integer.parseInt(mybundle.getString("LoginController.userflag")));
			log.info(token);
			final String refreshToken=jwtTokenUtil.generateRefreshToken();
			Token tokens=new Token();
			tokens.setJwtToken(token);
			tokens.setRefreshToken(refreshToken);
			valueOps.set(refreshToken, ls.getUserID(),Integer.parseInt(mybundle.getString("LoginController.timelimit")), TimeUnit.MINUTES);
			log.info(jwtTokenUtil.getUserIDFromToken(token));
			ls.setLoginStatusMessage("true");
			return ResponseEntity.ok(new JwtResponse(tokens, ls));
           
        }
     return ResponseEntity.ok(new JwtResponse("Invalid "));
       
    }

}

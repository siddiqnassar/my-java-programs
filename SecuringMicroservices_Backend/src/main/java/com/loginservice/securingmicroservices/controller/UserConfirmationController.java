package com.loginservice.securingmicroservices.controller;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.loginservice.securingmicroservices.config.JWTtokenUtil;
import com.loginservice.securingmicroservices.model.JwtRequest;
import com.loginservice.securingmicroservices.model.User;

@CrossOrigin
@RestController
public class UserConfirmationController {
	ResourceBundle mybundle = ResourceBundle.getBundle("userconfirmationcontroller");
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JWTtokenUtil jwt;
	
	@CrossOrigin(origins = "*")
	@PostMapping("/sendmail")
	public ResponseEntity<?> sendmail(@RequestBody User user)
	{
		String url=mybundle.getString("UserController.sendmailurl");
		
		Object responseSendMail = restTemplate.postForObject(url,user,Object.class);
		
		return ResponseEntity.ok(responseSendMail);
		
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/confirmmail/{uid}")
	public ResponseEntity<?> confirmmail(@PathVariable("uid") String token)
	{
		
		String url=mybundle.getString("UserController.confirmmailurl")+token;
		System.out.println("token backend");
		Object responseConfirmMail = restTemplate.getForEntity(url,Object.class);
		System.out.println("token backend after api");
		return ResponseEntity.ok(responseConfirmMail);
		
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getuseridfromtoken/{token}")
	public long getUserId(@PathVariable("token") String token)
	{
		String uid = jwt.getUserIDFromToken(token);
		long userId = Integer.parseInt(uid);
		
		return userId;
	}
	@CrossOrigin(origins = "*")
    @PostMapping("/userconfirmation/gettoken")
    public String getToken(@RequestBody User user)
    {
		JwtRequest jwtreq=new JwtRequest();
		jwtreq.setEmailID(user.getEmailID());
		jwtreq.setRole(user.getUserRole());
		jwtreq.setUserID(user.getUserID());
        String token = jwt.generateToken(jwtreq,jwtreq.getUserID(),Integer.parseInt(mybundle.getString("UserController.validitytimeindays")));
       return token;
    }
   
    @CrossOrigin(origins = "*")
    @GetMapping("/userconfirmation/validatetoken/{token}")
    public Boolean validateToken(@PathVariable("token") String token)
    {
   
        return jwt.isTokenExpired(token);
    }
}

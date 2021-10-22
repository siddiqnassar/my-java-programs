package com.loginservice.securingmicroservices.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private RestTemplate resttemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		String url="http://DATASERVICE:8017/api/data/user/find";
		com.loginservice.securingmicroservices.model.User p=new com.loginservice.securingmicroservices.model.User();
	    p.setEmailID(username);
	    com.loginservice.securingmicroservices.model.User u=resttemplate.postForObject(url, p, com.loginservice.securingmicroservices.model.User.class);
				if(u!=null)
					return new User(u.getEmailID(),u.getPasswordHistory().getPwd1(),new ArrayList<>());
				else
				{
					p.setUserID(Long.parseLong(username));
				    u=resttemplate.postForObject(url, p, com.loginservice.securingmicroservices.model.User.class);
				    if(u!=null)
				    	return new User(u.getEmailID(),u.getPasswordHistory().getPwd1(),new ArrayList<>());

				}
				
				throw new UsernameNotFoundException("User not found with username: " + username);
	}
}

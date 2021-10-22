package com.loginservice.securingmicroservices.controller;

 


import java.util.Date;
import java.util.concurrent.TimeUnit;

 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

 

import com.loginservice.securingmicroservices.WebSecurityConfig;
import com.loginservice.securingmicroservices.config.JWTtokenUtil;
import com.loginservice.securingmicroservices.model.JwtRequest;
import com.loginservice.securingmicroservices.model.JwtResponse;
import com.loginservice.securingmicroservices.model.LoginStatus;
import com.loginservice.securingmicroservices.model.OTPRequest;

 

@RestController
@CrossOrigin
public class LogoutController {
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
     @Autowired
        private JWTtokenUtil jwttokenutil;
     @Resource(name = "redisTemplate")
     private ValueOperations<String, Long> valueOps;
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/cancel")
    public boolean logout(HttpServletRequest htp) {
         
        String token=htp.getHeader("Authorization").substring(7);
        String id=jwttokenutil.getUserIDFromToken(token);
        Long userID=Long.parseLong(id);
        int expLong=jwttokenutil.getExpirationDateFromToken(token).getMinutes();
        long millis=System.currentTimeMillis(); 
        Date date=new Date(millis);
        int exp=date.getMinutes();
        System.out.println(userID);
        int timeRem=(int) (expLong-exp)>0?(int)(expLong-exp):(-1)*(int)(expLong-exp);
        System.out.println(timeRem);
        System.out.println(exp);
        System.out.println(expLong);
        valueOps.set(userID.toString(),userID, timeRem, TimeUnit.MINUTES);

 

        return true;

 

    }

 

    
}
 
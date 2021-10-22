package com.loginservice.securingmicroservices.controller;


import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.loginservice.securingmicroservices.config.JWTtokenUtil;
import com.loginservice.securingmicroservices.model.JwtRequest;
import com.loginservice.securingmicroservices.model.Token;
import com.loginservice.securingmicroservices.model.User;


@RestController
public class RefreshTokenController {
@Autowired
private JWTtokenUtil jwttokenutil;
@Autowired
private RestTemplate resttemplate;


public RestTemplate getResttemplate() {
    return resttemplate;
}
public void setResttemplate(RestTemplate resttemplate) {
    this.resttemplate = resttemplate;
}
public RedisTemplate<String, Object> getRedisTemplate() {
    return redisTemplate;
}
public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
}
public ValueOperations<String, Long> getValueOps() {
    return valueOps;
}
public void setValueOps(ValueOperations<String, Long> valueOps) {
    this.valueOps = valueOps;
}
ResourceBundle mybundle = ResourceBundle.getBundle("refreshtokencontroller");
@Autowired
RedisTemplate<String, Object> redisTemplate;


@Resource(name = "redisTemplate")
private ValueOperations<String, Long> valueOps;


    @CrossOrigin("*")
    @PostMapping("/getnewtoken")
    public Token getNewToken(HttpServletRequest htp)
    {
    	String refreshtoken=htp.getHeader("refreshtoken");
        Token token=new Token();
        User user=new User();
        Long userID=getId(refreshtoken);
        if(userID!=null) {
        user.setUserID(userID);
        String url=mybundle.getString("RefreshtokenController.gettokenurl");
        com.loginservice.securingmicroservices.model.User u=resttemplate.postForObject(url, user, com.loginservice.securingmicroservices.model.User.class);
        JwtRequest jwtreq=new JwtRequest();
        jwtreq.setEmailID(u.getEmailID());
        jwtreq.setUserID(u.getUserID());
        jwtreq.setRole(u.getUserRole());


    String newRefreshToken = jwttokenutil.generateRefreshToken();
    token.setRefreshToken(newRefreshToken);
    valueOps.set(newRefreshToken, userID, Integer.parseInt(mybundle.getString("RefreshtokenController.timelimit")), TimeUnit.MINUTES);
    
    token.setJwtToken(jwttokenutil.generateToken(jwtreq, jwtreq.getUserID(),0));
        }
    return token;
    }
    private Long getId(String refreshtoken) {
        // TODO Auto-generated method stub
        System.out.println(refreshtoken);
        String key = refreshtoken;
        
        if (valueOps.get(key) != null)
            return valueOps.get(key);
        
        return null;
    }
}
 











package com.loginservice.securingmicroservices.config;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

 

import javax.xml.bind.DatatypeConverter;

 

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

 

import com.loginservice.securingmicroservices.model.JwtRequest;

 

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

 

@Component
public class JWTtokenUtil implements Serializable{

 

    private static final long serialVersionUID = 1L;
    static long min = 3;
    static long sec = 60;
    public static final long JWT_TOKEN_VALIDITY = min*sec;
    
    @Value("${jwt.secret}")
    private String secret;
    public String getUserIDFromToken(String token) {
        return getClaimFromToken(token, Claims::getId);
    }
    public String getUserRoleFromToken(String token) {
        Claims claims = Jwts.parser()         
                   .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                   .parseClaimsJws(token).getBody();
        return claims.get("role").toString();
    }
    public int getAuthorizationLevelFromToken(String token) {
        Claims claims = Jwts.parser()         
                   .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                   .parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.get("authorizationlevel").toString());
    }
    
    public String getIPFromToken(String token) {
        Claims claims = Jwts.parser()         
                   .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                   .parseClaimsJws(token).getBody();
        return claims.get("ip").toString();
    }
    
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    //check if the token has expired
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    //generate token for user
    public String generateToken(JwtRequest userDetails,Long userID,int flag) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getRole());
        
        
        if(userDetails.getMultipleLogin()!=null)
            claims.put("ip", userDetails.getMultipleLogin().getIpAddress());
        return doGenerateToken(claims, userDetails.getEmailID(),userID,flag);
    }
    public String generateRefreshToken() {
         int lowerLimit = 97; 
          
           
            int upperLimit = 122; 
      
            Random random = new Random(); 
           
            
            StringBuilder r = new StringBuilder(10); 
      
            for (int i = 0; i < 10; i++) { 
      
                
                int nextRandomChar = lowerLimit 
                                     + (int)(random.nextInt()
                                             * (upperLimit - lowerLimit + 1)); 
      
                
                r.append((char)nextRandomChar); 
            } 
      return r.toString();
        
    }
    
    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialioation(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string 
    private String doGenerateToken(Map<String, Object> claims, String subject,Long userID,int flag) {
       
        
        
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setId(userID.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + (flag*24*57+1)*(JWT_TOKEN_VALIDITY*1000)))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
}

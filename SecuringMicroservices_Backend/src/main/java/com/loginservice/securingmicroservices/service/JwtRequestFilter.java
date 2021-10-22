package com.loginservice.securingmicroservices.service;

 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

 

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

 

import com.google.gson.Gson;
import com.loginservice.securingmicroservices.config.JWTtokenUtil;
import com.loginservice.securingmicroservices.model.User;

 

import io.jsonwebtoken.ExpiredJwtException;
import io.micrometer.core.instrument.util.IOUtils;

 

@Component
@CrossOrigin("*")
public class JwtRequestFilter extends OncePerRequestFilter {
    
    @Autowired
    private UserDetailsService jwtUserDetailsService;
    @Autowired
    private JWTtokenUtil jwtTokenUtil;
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
    
    public RedisTemplate<String, Long> redisTemplate() {
        RedisTemplate<String, Long> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
    RedisTemplate<String, Long> redisTemplate=redisTemplate();
    
    //@Resource(name = "redisTemplate")
    private ValueOperations<String, Long> valueOps=redisTemplate.opsForValue();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        logger.info("in request filter");
        redisTemplate.afterPropertiesSet();
        //HttpServletRequestWrapper wrapper=new HttpServletRequestWrapper(request);
        //String dummy = wrapper.getReader().lines().collect(Collectors.joining());
 //       System.out.println(dummy);
        HttpServletRequest request2 = new RequestWrapper((HttpServletRequest) request);
        request = request2;
        String dummy = request2.getReader().lines().collect(Collectors.joining());
        

 

        //ServletRequestWrapper requestWrapper = (ServletRequestWrapper) request;
        //String dummy = requestWrapper.getReader().lines().collect(Collectors.joining());
         System.out.println(dummy);
        System.out.println("READER : ");
        String url=null;
    
         url = request.getRequestURI();
//            
//            
    System.out.println(url);
        
        if (url.equals("/login")) {
        User p = new User();
        Gson g = new Gson();
        User u = g.fromJson(dummy, User.class);
        System.out.println(dummy);
        System.out.println(u);
    System.out.println(u.getEmailID());
    p.setUserID(u.getUserID());
    p.setEmailID(u.getEmailID());
    RestTemplate restTemplate = new RestTemplate();
        System.out.println(p);
        User obj = restTemplate.postForObject("http://localhost:8017/api/data/user/find", p, User.class);
        System.out.println("hoi ");
            System.out.println(obj);
            String id = Long.toString(obj.getUserID());
        System.out.println(id);
        System.out.println(valueOps);
        System.out.println(valueOps.get(id));
    if (valueOps.get(id) != null) {
        System.out.println("neha ji");

 

        redisTemplate.delete(id);
        }
            
        }

 


        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        System.out.println(request);
    
        
        logger.info(requestTokenHeader);
        
        
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            logger.info("token");

 

            try {

 

                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                if (username == null) {
                    username = jwtTokenUtil.getUserIDFromToken(jwtToken);
                }
            } catch (IllegalArgumentException e) {
                logger.info("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.info("JWT Token has expired");
            }
        } else {
            logger.info("JWT Token does not begin with Bearer String");
        }
        // Once we get the token validate it.
        logger.info(SecurityContextHolder.getContext().getAuthentication());

 

        if ((username != null) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails;

 

            System.out.println("hi oinside");
            userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

 

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.

 

//                System.out.println("neha");
//                    Long userID=Long.parseLong(jwtTokenUtil.getUserIDFromToken(jwtToken));
//                    System.out.println(request.getPathInfo());
//                    System.out.println(request.getRequestURI());
//                    if (valueOps.get(userID.toString()) == null)
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

 

            }
        }
        System.out.println("hi neha neha");
        
        chain.doFilter(request, response);
        System.out.println("hi from jwt req filter");
        //request.setAttribute("executeSecondFilter", false);
    }

 

    
}
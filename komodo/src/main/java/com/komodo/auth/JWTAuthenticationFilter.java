package com.komodo.auth;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.komodo.repository.UserRepository;

import org.springframework.security.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.komodo.auth.AuthConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private UserRepository userRepository;
  
  public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
  }
  
  
  
  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
    try {
      com.komodo.model.User creds = new ObjectMapper()
        .readValue(req.getInputStream(), com.komodo.model.User.class);

      return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          creds.getUsername(),
          creds.getPassword(),
          
          new ArrayList<>())
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
    String username = ((User) auth.getPrincipal()).getUsername();
    com.komodo.model.User newUser = userRepository.findByUsername(username);
    Long userId = newUser.getId();
    String HEADER_STRING2 = "UserId"; 
    String token = JWT.create()
      .withSubject(((User) auth.getPrincipal()).getUsername())
      .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .sign(HMAC512(SECRET.getBytes()));
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    res.addHeader(HEADER_STRING2, Long.toString(userId));
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
    super.unsuccessfulAuthentication(req, res, failed);
  }
}
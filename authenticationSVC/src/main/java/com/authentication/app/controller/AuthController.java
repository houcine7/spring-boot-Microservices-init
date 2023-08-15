package com.authentication.app.controller;


import com.authentication.app.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

   AuthService authService;

    AuthController(AuthService authService) {
        this.authService=authService;
    }


    @PostMapping("/register")
    ResponseEntity<Map> register(String grantType,
                                 String username, String password,
                                 String refreshToken, boolean withRefreshToken){

        try{
          Map<String,String> result = authService.register(username,password,withRefreshToken);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(Map.of("message",e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    ResponseEntity<Map> login(String grantType,
                                 String username, String password,
                                 String refreshToken, boolean withRefreshToken){

        try{
            Map<String,String> result = authService.authenticate(username,password,withRefreshToken);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(Map.of("message",e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

}

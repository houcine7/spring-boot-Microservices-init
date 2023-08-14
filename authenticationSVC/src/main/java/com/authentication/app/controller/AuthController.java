package com.authentication.app.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    JwtEncoder jwtEncoder;

    AuthController(JwtEncoder jwtEncoder){
        this.jwtEncoder = jwtEncoder;
    }



    @PostMapping("/register")
    ResponseEntity<Map> register(Authentication authentication){

        Instant instant =Instant.now();
        String scope =authentication.getAuthorities().stream()
                .map(item->item.getAuthority())
                .collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuer("authentication-svc")
                .issuedAt(instant)
                .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                .claim("scope",scope)
                .build();

        String jwtToken =jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

        return new ResponseEntity<>(Map.of("access_toekn",jwtToken),HttpStatus.OK);
    }

}

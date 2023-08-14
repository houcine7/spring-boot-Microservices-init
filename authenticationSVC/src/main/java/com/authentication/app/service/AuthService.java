package com.authentication.app.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {

    JwtEncoder jwtEncoder;
    AuthenticationManager authenticationManager;
    JwtDecoder jwtDecoder;
    UserDetailsService userDetailsService;

    AuthService(JwtEncoder jwtEncoder,
    AuthenticationManager authenticationManager,
    JwtDecoder jwtDecoder,
    UserDetailsService userDetailsService
    ){
        this.userDetailsService=userDetailsService;
        this.authenticationManager =authenticationManager;
        this.jwtDecoder=jwtDecoder;
        this.jwtEncoder=jwtEncoder;
    }

    public Map<String,String> authenticate(
            String grantType,
            String username, String password,
            String refreshToken, boolean withRefreshToken
    ){
        Map<String,String> result =new HashMap<>();

        String subject=null;
        String scope=null;
        if(grantType.equals("USERNAME_PASSWORD")) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
            subject =authentication.getName();
            scope =authentication.getAuthorities().stream()
                    .map(item->item.getAuthority())
                    .collect(Collectors.joining(" "));
        }
        else if(grantType.equals("REFRESH_TOKEN")){
            Jwt decodedToken = jwtDecoder.decode(refreshToken);
            subject =decodedToken.getSubject();
            UserDetails user =userDetailsService.loadUserByUsername(subject);
            scope =user.getAuthorities().stream()
                    .map(
                            authority-> authority.getAuthority()
                    )   .collect(Collectors.joining(" "));

        }

        Instant instant =Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuer("authentication-svc")
                .issuedAt(instant)
                .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                .claim("scope",scope)
                .build();

        String jwtToken =jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        result.put("accessToken",jwtToken);
        if(withRefreshToken){
            JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuer("authentication-svc")
                    .issuedAt(instant)
                    .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                    .build();
            String  jwtRefreshToken= jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
            result.put("refreshToken" , jwtRefreshToken);
        }


        return result;
    }
}

package com.authentication.app.service;

import com.authentication.app.model.UserDetailsImp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;


@Service
public class JwtServices {

    JwtEncoder jwtEncoder;
    JwtDecoder jwtDecoder;

    JwtServices(final JwtEncoder jwtEncoder , final JwtDecoder jwtDecoder
    ){
        this.jwtEncoder=jwtEncoder;
        this.jwtDecoder=jwtDecoder;
    }

    public String generateToken(String scope, String subject,boolean isAccessToken){

        Instant instant =Instant.now();
        JwtClaimsSet jwtClaimsSet=null;

      if(isAccessToken)
          jwtClaimsSet = JwtClaimsSet.builder()
                  .subject(subject)
                  .issuer("authentication-svc")
                  .issuedAt(instant)
                  .expiresAt(instant.plus(1, ChronoUnit.MINUTES))
                  .claim("scope",scope)
                  .build();
      else
          jwtClaimsSet =JwtClaimsSet.builder()
                  .subject(subject)
                  .issuer("authentication-svc")
                  .issuedAt(instant)
                  .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                  .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

    }

    public String getSubject(String token){
        Jwt decodedToken = jwtDecoder.decode(token);
        return decodedToken.getSubject();
    }


}

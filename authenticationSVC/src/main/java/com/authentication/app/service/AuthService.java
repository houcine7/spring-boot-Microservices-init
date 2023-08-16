package com.authentication.app.service;


import com.authentication.app.Enums.Role;
import com.authentication.app.entity.UserEntity;
import com.authentication.app.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {
    AuthenticationManager authenticationManager;
    UserDetailsService userDetailsService;
    UserRepository userRepository;
    JwtServices jwtServices;
    PasswordEncoder passwordEncoder;

    AuthService(
    AuthenticationManager authenticationManager, JwtServices jwtServices,
    UserDetailsService userDetailsService,
    UserRepository userRepository, PasswordEncoder passwordEncoder
    ){
        this.userDetailsService=userDetailsService;
        this.authenticationManager =authenticationManager;
        this.jwtServices=jwtServices;
        this.userRepository= userRepository;
        this.passwordEncoder=passwordEncoder;

    }


    // Exception's to be defined later on
    public Map<String,String> register(String username,
                                       String password ,boolean withRefreshToken) throws Exception {

        UserEntity user =null ;
        try{
            user = UserEntity.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .address(null)
                    .roles(List.of(Role.USER))
                    .build();

            var insertedUser= userRepository.save(user);

        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new  Exception("this username can't be used already exist try to choose an other one");
        }
        return this.authenticate(username,password,withRefreshToken);
    }


    public Map<String,String> refreshToken(String refreshToken){

        String subject =jwtServices.getSubject(refreshToken);

        UserDetails user =userDetailsService.loadUserByUsername(subject);
        String scope =user.getAuthorities().stream()
                .map(
                        authority-> authority.getAuthority()
                ).collect(Collectors.joining(" "));
        String accessToken=jwtServices.generateToken(scope,subject,true);
        return Map.of("accessToken",accessToken);
    }


    public Map<String,String> authenticate(
            String username, String password,
             boolean withRefreshToken
    ) throws Exception {

        Map<String,String> result =new HashMap<>();
        String subject=null;
        String scope=null;
        Authentication authentication=null;
        try{
            authentication= authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.getStackTrace();
            throw new Exception("Bad credentials");
        }

        System.out.println(authentication.toString());
        subject =authentication.getName();
        scope =authentication.getAuthorities().stream()
                .map(item->item.getAuthority())
                .collect(Collectors.joining(" "));
        System.out.println(scope);
        String accessToken=jwtServices.generateToken(scope,subject,true);
        result.put("accessToken",accessToken);
        if(withRefreshToken){
            String  jwtRefreshToken=  jwtServices.generateToken(null,subject,false);
            result.put("refreshToken" , jwtRefreshToken);
        }
        return result;
    }
}

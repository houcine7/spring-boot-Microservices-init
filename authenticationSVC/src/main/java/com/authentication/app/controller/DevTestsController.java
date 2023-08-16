package com.authentication.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class DevTestsController {

    @GetMapping("/data")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    ResponseEntity<Map<String,String>> getInfo(Authentication authentication){
        System.out.println("here");
        System.out.println(authentication.getAuthorities() +"----"+ authentication.getName());
        return new ResponseEntity<>(Map.of("test","user" ), HttpStatus.OK)

    @PostMapping("/data")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    ResponseEntity<Map<String,String>> post(String data){
        System.out.println(data);
        return new ResponseEntity<>(Map.of("test","admin" ), HttpStatus.OK);
    }

}

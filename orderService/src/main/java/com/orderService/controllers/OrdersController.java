package com.orderService.controllers;


import com.orderService.configuration.VaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    VaultConfig vaultConfig;

    @GetMapping("/")
    ResponseEntity<String> checkService(){
        return new ResponseEntity<>("IT'S WORKING", HttpStatus.OK);
    }


    @GetMapping("/configs")
    ResponseEntity<Object> getConfigs(){
        return new ResponseEntity<>(vaultConfig,HttpStatus.OK);
    }
}

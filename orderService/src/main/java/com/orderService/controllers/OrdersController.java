package com.orderService.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @GetMapping("/")
    ResponseEntity<String> checkService(){
        return new ResponseEntity<>("IT'S WORKING", HttpStatus.OK);
    }

}

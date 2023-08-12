package com.billingService.models;


import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private String email;
    private String address;
}

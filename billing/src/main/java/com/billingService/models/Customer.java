package com.billingService.models;


import lombok.Data;

@Data
public class Customer {
    private long id;
    private String name;
    private String email;
    private String address;
}

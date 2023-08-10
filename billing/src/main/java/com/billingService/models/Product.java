package com.billingService.models;


import lombok.Data;

@Data
public class Product {
    private double price ;
    private int quantity;
    private long id;
    private String name ;
}

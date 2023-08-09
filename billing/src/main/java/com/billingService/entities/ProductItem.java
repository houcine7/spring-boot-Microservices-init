package com.billingService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductItem {
    @Id
    private long id;
    private Long productId;
    private double unitPrice;
    private int quantity;
    private Bill bill;
    private double discount;
}

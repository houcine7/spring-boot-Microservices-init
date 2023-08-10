package com.billingService.entities;

import com.billingService.models.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long productId;
    private double unitPrice;
    private int quantity;
    @ManyToOne
    private Bill bill;
    private double discount;

    @Transient
    private Product product;

}

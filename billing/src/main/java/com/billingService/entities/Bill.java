package com.billingService.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
public class Bill {
    @Id
    private Long id;
    private Date billDate;
    private long customerId;
    @OneToMany
    private List<ProductItem> productItems;

}

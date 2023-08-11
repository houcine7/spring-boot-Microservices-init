package com.billingService.services;


import com.billingService.models.Customer;
import com.billingService.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/customers/{customerId}")
    Customer getCustomerById(@PathVariable long customerId);


    @GetMapping("/customers")
    PagedModel<Product> getAllProducts();
}

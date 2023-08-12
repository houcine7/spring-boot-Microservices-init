package com.billingService.services;


import com.billingService.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE") // this a declarative dependency to for rest client
// there is also restTemplate that offers a programmatic way for interacting with a rest api
public interface ProductRestClient {
    @GetMapping("/products/{productId}")
    Product findProductById(@PathVariable long productId);

    @GetMapping("/products")
    List<Product> getAllProducts();
}

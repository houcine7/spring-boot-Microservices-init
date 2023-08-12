package com.productsService.controllers;


import com.productsService.Entities.Product;
import com.productsService.Repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Products {

    @Autowired
    ProductsRepository productsRepository;

    @GetMapping("/products")
    ResponseEntity<List<Product>> getProducts() {
        try {
            List<Product> ls = productsRepository.findAll();
            return new ResponseEntity<>(ls, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping("/products/{id}")
    ResponseEntity<Product> getProduct(@PathVariable Long id) {
        try {
            Product result = productsRepository.findById(id).get();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

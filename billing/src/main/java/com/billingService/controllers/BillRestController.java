package com.billingService.controllers;


import com.billingService.entities.Bill;
import com.billingService.entities.ProductItem;
import com.billingService.repositories.BillRepository;
import com.billingService.repositories.ProductItemRepository;
import com.billingService.services.CustomerRestClient;
import com.billingService.services.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/*
*  TO CHECK LATER : the best practice to inject the dependency
*   of the class (using atuowired vs constructor)
*/


@RestController
public class BillRestController {

    @Autowired
    BillRepository billRepository;
    @Autowired
    ProductItemRepository productItemRepository;
    @Autowired
    CustomerRestClient customerRestClient;
    @Autowired
    ProductRestClient productRestClient;

    @GetMapping("/fullObject/{id}")
    ResponseEntity<Bill> getBillById(@PathVariable Long id){

        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));

        bill.getProductItems().forEach(item->{
            item.setProduct(productRestClient.findProductById(item.getProductId()));
        });

        return new ResponseEntity<Bill>(bill , HttpStatus.OK);
    }


}

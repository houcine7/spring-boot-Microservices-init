package com.billingService.repositories;


import com.billingService.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;


public interface BillRepository extends JpaRepository<Bill,Long> {
}

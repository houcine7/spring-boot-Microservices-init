package com.customerService.Repositories;

import com.customerService.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CustomersRepository extends JpaRepository<Customer,Long> {
}

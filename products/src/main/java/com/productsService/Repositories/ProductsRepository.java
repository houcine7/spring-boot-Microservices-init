package com.productsService.Repositories;

import com.productsService.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface ProductsRepository extends JpaRepository<Product,Long> {
}

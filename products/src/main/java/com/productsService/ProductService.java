package com.productsService;

import com.productsService.Entities.Product;
import com.productsService.Repositories.ProductsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductService {

	public static void main(String[] args) {

		SpringApplication.run(ProductService.class, args);
	}
	@Bean
	CommandLineRunner start(ProductsRepository productsRepository){
		return args->{
			productsRepository.save(new Product(
						null,
						"product 1",
						"25 MAD",
					50
				));

			productsRepository.save(new Product(
					null,
					"product 2",
					"18 MAD",
					100
			));
			productsRepository.save(new Product(
					null,
					"product 3",
					"10 MAD",
					15
			));
		};
	}

}

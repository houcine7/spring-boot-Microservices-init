package com.customerService;

import com.customerService.Entities.Customer;
import com.customerService.Repositories.CustomersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerService {

	public static void main(String[] args) {

		SpringApplication.run(CustomerService.class, args);
	}

	@Bean
	CommandLineRunner start(CustomersRepository customersRepository){
		return args->{
				customersRepository.save(new Customer(
						null,
						"houcine",
						"houcine"
				));

			customersRepository.save(new Customer(
					null,
					"houcine1",
					"houcine1"
			));
			customersRepository.save(new Customer(
					null,
					"houcine1",
					"houcine1"
			));
		};
	}



}

package com.billingService;

import com.billingService.entities.Bill;
import com.billingService.entities.ProductItem;
import com.billingService.models.Customer;
import com.billingService.models.Product;
import com.billingService.repositories.BillRepository;
import com.billingService.repositories.ProductItemRepository;
import com.billingService.services.CustomerRestClient;
import com.billingService.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;


@SpringBootApplication
@EnableFeignClients
public class BillingService {

	public static void main(String[] args) {
		SpringApplication.run(BillingService.class, args);
	}


	@Bean
	CommandLineRunner start(BillRepository billRepository ,
							ProductItemRepository productItemRepository,
							CustomerRestClient customerRestClient,
							ProductRestClient productRestClient){

		return args -> {
			Collection<Product> products = customerRestClient.getAllProducts().getContent();

			Long customerId= 1L;
			Customer customer =customerRestClient.getCustomerById(customerId);

			if(customer==null)
				throw new RuntimeException("Customer not found ");
			//Bill bill =new Bill();

			Bill bill =Bill.builder()
					.billDate(new Date())
					.customerId(customerId)
					.build();

			Bill savedBill= billRepository.save(bill);

			products.forEach(item->{
				ProductItem pItem =ProductItem.builder()
						.productId(item.getId())
						.quantity(new Random().nextInt(20)+1)
						.bill(savedBill)
						.unitPrice(item.getPrice())
						.discount(Math.random())
						.build();
				productItemRepository.save(pItem);
			});

		};

	}
}

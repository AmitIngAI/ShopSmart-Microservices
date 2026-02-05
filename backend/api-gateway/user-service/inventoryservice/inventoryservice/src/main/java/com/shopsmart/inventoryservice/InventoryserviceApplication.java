package com.shopsmart.inventoryservice; // Apna package name wahi rakhein jo pehle tha

import com.shopsmart.inventoryservice.model.Inventory;
import com.shopsmart.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableDiscoveryClient // Agar Eureka use kar rahe hain to ise uncomment karein (Spring Boot 3 me aksar zarurat nahi padti)
public class InventoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryserviceApplication.class, args);
	}

	// --- YE NAYA CODE HAI ---
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			// Pehla product: iPhone 15 (Stock me hai)
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_15");
			inventory.setQuantity(100);

			// Dusra product: iPhone 15 Red (Out of stock)
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone_15_red");
			inventory1.setQuantity(0);

			// Database me save karna
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);

			System.out.println("Data Loaded Successfully!");
		};
	}
}
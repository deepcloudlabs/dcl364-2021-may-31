package com.example.stockmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS)
public class StockmarketBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockmarketBootApplication.class, args);
	}

}

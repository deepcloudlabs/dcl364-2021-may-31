package com.example.stockmarket.repository;

import java.util.Collection;

import com.example.stockmarket.entity.Stock;

public interface StockRepository extends GenericRepository<Stock, String> {
	Collection<Stock> findByCompany(String company);

	Collection<Stock> findByPriceBetween(double min, double max);
}

package com.example.stockmarket.service;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.event.StockPriceChangedEvent;
import com.example.stockmarket.repository.StockRepository;

@Stateless
public class StockMarketEmulator {
	@Inject
	private StockRepository stockRepository;
	@Inject
	private Event<StockPriceChangedEvent> publisher;
	
	@Schedule(second = "*", hour = "*", minute = "*",persistent = false)
	public void updateStockPriceRandomly() {
		stockRepository.findAll(0, 100)
		               .forEach( stock -> {
		            	   var oldPrice = stock.getPrice();
		            	   var newPrice = getRandomPrice(stock);
		            	   var symbol = stock.getSymbol();
						   stock.setPrice(newPrice);
						   stockRepository.update(stock);
						   var event = new StockPriceChangedEvent(symbol, oldPrice, newPrice);
						   System.err.println("Firing event: "+event);
						   publisher.fire(event);
		               });
	}

	private double getRandomPrice(Stock stock) {
		return stock.getPrice() * ( 1.0 + (ThreadLocalRandom.current().nextDouble(1)-0.5)* 0.01);
	}
}

package com.example.stockmarket.service;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.Json;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.event.StockPriceChangedEvent;
import com.example.stockmarket.repository.StockRepository;

@Stateless
public class StockMarketProducer {
	@Inject
	private StockRepository stockRepository;
	@Inject
	private JMSContext jmsContext;
	
	@Resource(mappedName = "java:/jms/queue/stockQueue") // JNDI Name
	private Queue queue;
	
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
						   jmsContext.createProducer().send(queue, toJson(event));
		               });
	}

	private double getRandomPrice(Stock stock) {
		return stock.getPrice() * ( 1.0 + (ThreadLocalRandom.current().nextDouble(1)-0.5)* 0.01);
	}
	
	private String toJson(StockPriceChangedEvent event) {
		return Json.createObjectBuilder()
			       .add("symbol", event.getSymbol())
			       .add("old_price", event.getOldPrice())
			       .add("new_price", event.getNewPrice())
			       .build()
			       .toString();
	}
}

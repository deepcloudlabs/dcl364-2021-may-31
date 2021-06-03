package com.example.stockmarket.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import com.example.stockmarket.event.StockPriceChangedEvent;

@Stateless
public class AlgoTradingService {

	public void listenEvent(@Observes StockPriceChangedEvent event) {
		System.err.println("Event received: "+event);
	}
}

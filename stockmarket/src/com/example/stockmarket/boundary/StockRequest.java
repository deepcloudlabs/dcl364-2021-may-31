package com.example.stockmarket.boundary;

import com.example.stockmarket.entity.Stock;

public class StockRequest {
	private String symbol;
	private String description;
	private String company;
	private double price;

	public StockRequest() {
	}

	public StockRequest(String symbol, String description, String company, double price) {
		this.symbol = symbol;
		this.description = description;
		this.company = company;
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "StockRequest [symbol=" + symbol + ", description=" + description + ", company=" + company + ", price="
				+ price + "]";
	}

	public Stock toStock() {
		return new Stock(symbol, description, company, price);
	}

}

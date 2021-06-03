package com.example.stockmarket.boundary;

public class StockResponse {
	private String status;
	private String symbol;
	private double price;

	public StockResponse() {
	}

	public StockResponse(String status) {
		this.status = status;
	}

	public StockResponse(String status, String symbol, double price) {
		this.status = status;
		this.symbol = symbol;
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "StockResponse [status=" + status + ", symbol=" + symbol + ", price=" + price + "]";
	}

}

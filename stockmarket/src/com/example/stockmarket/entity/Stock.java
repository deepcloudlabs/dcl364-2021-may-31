package com.example.stockmarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "stocks")
@NamedQueries({
	@NamedQuery(name = "Stock.findAll", query = "select s from Stock s"),
	@NamedQuery(name = "Stock.findByCompany", query = "select s from Stock s where s.company=:company"),
	@NamedQuery(name = "Stock.findByPriceBetween", query = "select s from Stock s where s.price between :min and :max")
})
public class Stock {
	@Id
	private String symbol;
	@Column(name = "aciklama")
	private String description;
	private String company;
	@Column(name = "fiyat")
	private double price;

	public Stock() {
	}

	public Stock(String symbol, String description, String company, double price) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", description=" + description + ", company=" + company + ", price=" + price
				+ "]";
	}

}

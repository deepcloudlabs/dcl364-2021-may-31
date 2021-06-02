package com.example.stockmarket.repository.jpa;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.repository.StockRepository;

@Named
@Singleton
public class JpaStockRepository implements StockRepository {
	@PersistenceContext(unitName = "stockmarketPU")
	private EntityManager entityManager;

	@Override
	public Optional<Stock> findByIdentity(String symbol) {
		return Optional.ofNullable(entityManager.find(Stock.class, symbol));
	}

	@Override
	public Collection<Stock> findAll(String key, int pageNo, int pageSize) {
		var offset = pageNo * pageSize;
		return entityManager.createNamedQuery("Stock.findAll", Stock.class).setFirstResult(offset)
				.setMaxResults(pageSize).getResultList();
	}

	@Override
	@Transactional
	public Stock create(Stock stock) {
		entityManager.persist(stock);
		return stock;
	}

	@Override
	@Transactional
	public Stock update(Stock stock) {
		var symbol = stock.getSymbol();
		var managedStock = findByIdentity(symbol);
		if (managedStock.isEmpty())
			throw new IllegalArgumentException("Cannot find stock to update!");
		var updatedStock = managedStock.get();
		updatedStock.setPrice(stock.getPrice());
		updatedStock.setCompany(stock.getCompany());
		return updatedStock;
	}

	@Override
	@Transactional
	public Optional<Stock> removeByIdentity(String symbol) {
		var managedStock = findByIdentity(symbol);
		if (managedStock.isEmpty())
			return Optional.empty();
		var removedStock = managedStock.get();
		entityManager.remove(removedStock);
		return Optional.of(removedStock);
	}

	@Override
	public Collection<Stock> findByCompany(String company) {
		return entityManager.createNamedQuery("Stock.findByCompany", Stock.class)
				            .setParameter("company", company)
				            .getResultList();
	}

	@Override
	public Collection<Stock> findByPriceBetween(double min, double max) {
		return entityManager.createNamedQuery("Stock.findByPriceBetween", Stock.class)
							.setParameter("min", min)
							.setParameter("max", max)
				            .getResultList();
	}

}

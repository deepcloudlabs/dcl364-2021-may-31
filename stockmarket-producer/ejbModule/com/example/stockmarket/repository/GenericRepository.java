package com.example.stockmarket.repository;

import java.util.Collection;
import java.util.Optional;

public interface GenericRepository<E,K> {
	Optional<E> findByIdentity(K key);
	Collection<E> findAll(int pageNo,int pageSize);
	E create(E e);
	E update(E e);
	Optional<E> removeByIdentity(K key);	
}

package com.example.crm.infra;

public interface EventPublisher<E> {
	public void publish(E e);
}

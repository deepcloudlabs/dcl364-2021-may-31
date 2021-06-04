package com.example.crm.adapter;

import java.util.Objects;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;

import com.example.crm.domain.Customer;
import com.example.crm.domain.TcKimlikNo;
import com.example.crm.entity.CustomerEntity;
import com.example.crm.repository.CustomerRepository;

@Stateless
public class CustomerRepositoryJpaAdapter implements CustomerRepository {
	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	private ModelMapper modelMapper;
	
	@Override
	public void save(Customer customer) {
		// Customer (domain) -> CustomerEntity (Technology)
		entityManager.persist(modelMapper.map(customer, CustomerEntity.class));
	}

	@Override
	public boolean exists(TcKimlikNo kimlikNo) {
		return Objects.nonNull(entityManager.find(CustomerEntity.class,kimlikNo.getValue()));
	}

	@Override
	public Optional<Customer> find(TcKimlikNo kimlik) {
		var customerEntity = entityManager.find(CustomerEntity.class,kimlik.getValue());
		if (Objects.isNull(customerEntity))
			return Optional.empty();
		return Optional.of(modelMapper.map(customerEntity, Customer.class));
	}

	@Override
	public void remove(Customer customer) {
		var customerEntity = entityManager.find(CustomerEntity.class,customer.getKimlikNo().getValue());
		if (Objects.nonNull(customerEntity))
		   entityManager.remove(customerEntity);
	}

}

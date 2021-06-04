package com.example.crm.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crm.domain.Customer;
import com.example.crm.domain.TcKimlikNo;
import com.example.crm.entity.CustomerEntity;
import com.example.crm.repository.CustomerEntityRepository;
import com.example.crm.repository.CustomerRepository;

@Service
public class CustomerRepositoryJpaAdapter implements CustomerRepository {
	@Autowired
	private CustomerEntityRepository repo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void save(Customer customer) {
		repo.save(modelMapper.map(customer, CustomerEntity.class));
	}

	@Override
	public boolean exists(TcKimlikNo kimlikNo) {
		return repo.existsById(kimlikNo.getValue());
	}

	@Override
	public Optional<Customer> find(TcKimlikNo kimlik) {
		var customerEntity = repo.findById(kimlik.getValue());
		if (customerEntity.isEmpty())
			return Optional.empty();
		return Optional.of(modelMapper.map(customerEntity.get(), Customer.class));
	}

	@Override
	public void remove(Customer customer) {
		repo.deleteById(customer.getKimlikNo().getValue());
	}

}

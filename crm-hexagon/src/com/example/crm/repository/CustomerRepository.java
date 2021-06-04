package com.example.crm.repository;

import java.util.Optional;

import com.example.crm.domain.Customer;
import com.example.crm.domain.TcKimlikNo;

public interface CustomerRepository {

	void save(Customer customer);

	boolean exists(TcKimlikNo kimlikNo);

	Optional<Customer> find(TcKimlikNo kimlik);

	void remove(Customer managedCustomer);

}

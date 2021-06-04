package com.example.crm.application.business;

import com.example.crm.application.CrmApplication;
import com.example.crm.application.business.event.CustomerAcquiredEvent;
import com.example.crm.application.business.event.CustomerEvent;
import com.example.crm.application.business.event.CustomerReleasedEvent;
import com.example.crm.domain.Customer;
import com.example.crm.domain.TcKimlikNo;
import com.example.crm.infra.EventPublisher;
import com.example.crm.repository.CustomerRepository;

public class StandardCrmApplication implements CrmApplication {
	//dependencies -> i) repository ii) infrastructure
	private CustomerRepository customerRepository; // (i)
	private EventPublisher<CustomerEvent> eventPublisher; // (ii)

	// Dependency Injection through constructor
	public StandardCrmApplication(CustomerRepository customerRepository, EventPublisher<CustomerEvent> eventPublisher) {
		this.customerRepository = customerRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void acquireCustomer(Customer customer) {
		var kimlikNo = customer.getKimlikNo();
		if (customerRepository.exists(kimlikNo))
			throw new IllegalArgumentException("Customer already exists");
		customerRepository.save(customer);
		var event = new CustomerAcquiredEvent(kimlikNo);
		eventPublisher.publish(event);
	}

	@Override
	public Customer releaseCustomer(TcKimlikNo kimlik) {
		var customer = customerRepository.find(kimlik);
		if (customer.isEmpty())
			throw new IllegalArgumentException("Customer does not exist");
		var managedCustomer = customer.get();
		customerRepository.remove(managedCustomer);
		var event = new CustomerReleasedEvent(kimlik);
		eventPublisher.publish(event);
		return managedCustomer;
	}

}

package com.example.crm.config;

import java.util.stream.Collectors;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import com.example.crm.application.CrmApplication;
import com.example.crm.application.business.StandardCrmApplication;
import com.example.crm.application.business.event.CustomerEvent;
import com.example.crm.boundary.AcquireCustomerRequest;
import com.example.crm.domain.Address;
import com.example.crm.domain.Customer;
import com.example.crm.domain.Phone;
import com.example.crm.domain.TcKimlikNo;
import com.example.crm.entity.AddressEntity;
import com.example.crm.entity.CustomerEntity;
import com.example.crm.entity.PhoneEntity;
import com.example.crm.infra.EventPublisher;
import com.example.crm.repository.CustomerRepository;

@Named
@Singleton
public class AppConfig {
	private static final Converter<Customer, CustomerEntity>
	     CUSTOMER_TO_CUSTOMER_ENTITY_CONVERTER = (context) -> {
	    	 var customer = context.getSource();
	    	 var customerEntity = new CustomerEntity();
	    	 customerEntity.setId(customer.getKimlikNo().getValue());
	    	 customerEntity.setFullname(customer.getFullname().getFull());
	    	 customerEntity.setEmail(customer.getEmail().getValue());
	    	 var phones = customer.getPhones().stream().map( (Phone phone) -> new PhoneEntity(phone.getValue(),phone.getType())).collect(Collectors.toList());
	    	 var addresses = customer.getAddresses().stream().map( (Address address) -> new AddressEntity(address.getValue())).collect(Collectors.toList());
	    	 customerEntity.setPhones(phones);
	    	 customerEntity.setAddresses(addresses);
	    	 return customerEntity;
	     };
	     
	     private static final Converter<CustomerEntity, Customer>
	     CUSTOMER_ENTITY_TO_CUSTOMER_CONVERTER = (MappingContext<CustomerEntity, Customer> context) -> {
	    	 CustomerEntity customerEntity = context.getSource();
	    	 var names = customerEntity.getFullname().split("\\s+");
	    	 return new Customer.Builder(TcKimlikNo.of(customerEntity.getId()))
	    			             .fullname(names[0], names[1])
	    			             .email(customerEntity.getEmail())
	    			             .phones(customerEntity.getPhones().stream().map(PhoneEntity::getNumber).collect(Collectors.toList()))
	    			             .addresses(customerEntity.getAddresses().stream().map(AddressEntity::getValue).collect(Collectors.toList()))
	    			             .build();
	     };
	     private static final Converter<AcquireCustomerRequest, Customer>
	     ACQUIRE_CUSTOMER_REQUEST_TO_CUSTOMER_CONVERTER = (context) -> {
	    	 var request = context.getSource();
	    	 var names = request.getFullname().split("\\s+");
	    	 return new Customer.Builder(TcKimlikNo.of(request.getId()))
	    			 .fullname(names[0], names[1])
	    			 .email(request.getEmail())
	    			 .phones(request.getPhones().stream().map(PhoneEntity::getNumber).collect(Collectors.toList()))
	    			 .addresses(request.getAddresses().stream().map(AddressEntity::getValue).collect(Collectors.toList()))
	    			 .build();
	     };
	      
	@Inject
	private CustomerRepository customerRepository; // Adapter -> GoF's Adapter Pattern
	
	@Inject
	private EventPublisher<CustomerEvent> eventPublisher; // Adapter

	@Produces
	@Named
	@Singleton
	public CrmApplication createCrmApplication() {
		return new StandardCrmApplication(customerRepository,eventPublisher);
	}
	
	@Produces
	@Named
	@Singleton
	public ModelMapper createModelMapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(CUSTOMER_TO_CUSTOMER_ENTITY_CONVERTER, Customer.class, CustomerEntity.class);
		mapper.addConverter(CUSTOMER_ENTITY_TO_CUSTOMER_CONVERTER, CustomerEntity.class, Customer.class);
		mapper.addConverter(ACQUIRE_CUSTOMER_REQUEST_TO_CUSTOMER_CONVERTER, AcquireCustomerRequest.class, Customer.class);
		return mapper;
	}
}

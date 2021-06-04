package com.example.crm.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.modelmapper.ModelMapper;

import com.example.crm.application.CrmApplication;
import com.example.crm.boundary.AcquireCustomerRequest;
import com.example.crm.boundary.AcquireCustomerResponse;
import com.example.crm.boundary.ReleaseCustomerResponse;
import com.example.crm.domain.Customer;
import com.example.crm.domain.TcKimlikNo;

@Stateless
public class CrmService {

	@Inject
	private CrmApplication crmApplication;
	@Inject
	private ModelMapper modelMapper;
	
	public AcquireCustomerResponse acquireCustomer(AcquireCustomerRequest request) {
		var customer = modelMapper.map(request, Customer.class);
		crmApplication.acquireCustomer(customer);
		return new AcquireCustomerResponse("success");
	}

	public ReleaseCustomerResponse releaseCustomer(String identity) {
		crmApplication.releaseCustomer(TcKimlikNo.of(identity));
		return new ReleaseCustomerResponse("success");
	}
	
}

package com.example.crm.application;

import com.example.crm.domain.Customer;
import com.example.crm.domain.TcKimlikNo;

// Contract -> Single Business Capability 
// SOLID -> Interface Segregation Principle (ISP)
// SRP -> High Cohesion -> Interface -> ✘ Fat Interface
public interface CrmApplication { 
	public void acquireCustomer(Customer customer);
	public Customer releaseCustomer(TcKimlikNo kimlik);
}

package com.example.crm.boundary;

import java.util.List;

import com.example.crm.entity.AddressEntity;
import com.example.crm.entity.PhoneEntity;

public class AcquireCustomerRequest {
	private String id;
	private String fullname;
	private String email;
	private List<PhoneEntity> phones;
	private List<AddressEntity> addresses;

	public AcquireCustomerRequest() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PhoneEntity> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneEntity> phones) {
		this.phones = phones;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "AcquireCustomerRequest [id=" + id + ", fullname=" + fullname + ", email=" + email + ", phones=" + phones
				+ ", addresses=" + addresses + "]";
	}

}

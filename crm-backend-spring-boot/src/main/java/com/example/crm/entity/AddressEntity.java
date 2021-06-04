package com.example.crm.entity;

import javax.persistence.Embeddable;

@Embeddable
public class AddressEntity {
	private String value;

	public AddressEntity() {
	}

	public AddressEntity(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Address [value=" + value + "]";
	}

}

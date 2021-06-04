package com.example.crm.entity;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import com.example.crm.domain.PhoneType;

@Embeddable
public class PhoneEntity {
	private String number;
	@Enumerated
	private PhoneType type;

	public PhoneEntity() {
	}

	public PhoneEntity(String number, PhoneType type) {
		this.number = number;
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Phone [number=" + number + ", type=" + type + "]";
	}

}

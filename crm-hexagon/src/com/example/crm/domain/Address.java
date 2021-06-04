package com.example.crm.domain;

import java.util.Objects;

@ValueObject
public final class Address {
	private final String value;

	private Address(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Address valueOf(String value) {
		Objects.requireNonNull(value);
		return new Address(value);
	}
}

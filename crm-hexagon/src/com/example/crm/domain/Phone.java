package com.example.crm.domain;

import java.util.Objects;

@ValueObject
public final class Phone {
	private final String value;
	private final PhoneType type;

	public Phone(String value) {
		this(value, PhoneType.MOBILE);
	}

	public Phone(String value, PhoneType type) {
		this.value = value;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public PhoneType getType() {
		return type;
	}

	public static Phone of(String value, PhoneType type) {
		Objects.requireNonNull(value);
		Objects.requireNonNull(type);
		return new Phone(value, type);
	}

	public static Phone of(String value) {
		return of(value, PhoneType.MOBILE);
	}
}

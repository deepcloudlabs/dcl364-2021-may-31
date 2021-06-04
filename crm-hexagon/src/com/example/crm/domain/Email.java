package com.example.crm.domain;

@ValueObject
public final class Email {
	private final String value;

	private Email(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static Email valueOf(String value) {
		if (!value.matches("^.*@.*$")) throw new IllegalArgumentException("This is not a valid email!");
		return new Email(value);
	}
}

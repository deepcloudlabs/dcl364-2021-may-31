package com.example.crm.domain;

import java.util.Objects;

@ValueObject
public final class FullName {
	private final String first;
	private final String last;

	private FullName(String first, String last) {
		this.first = first;
		this.last = last;
	}

	public static FullName of(String first, String last) {
		Objects.requireNonNull(first);
		Objects.requireNonNull(last);
		return new FullName(first, last);
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

}

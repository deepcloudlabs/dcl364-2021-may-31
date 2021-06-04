package com.example.crm.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

//DDD: Value Object -> i) Holds value 
//    ii) Does not have identity 
//   iii) Immutable Class -> i) Thread-safe ii) Cachable!
@ValueObject
public final class TcKimlikNo {
	private final String value;
	private static final Map<String, TcKimlikNo> cache = new ConcurrentHashMap<>();

	private TcKimlikNo(String value) { // is it valid?
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static TcKimlikNo of(String value) {
		// validation
		if (!isValid(value))
			throw new IllegalArgumentException("This is not a valid identity no!");
		// caching
		var cachedIdentity = cache.get(value);
		if (Objects.isNull(cachedIdentity)) {
			cachedIdentity = new TcKimlikNo(value);
			cache.put(value, cachedIdentity);
		}
		return cachedIdentity;
	}

	private static boolean isValid(String value) {
		return true;
	}
}

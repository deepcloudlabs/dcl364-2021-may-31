package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import com.example.lottery.service.RandomNumberService;

// Ctrl+Shift+F
public class FastRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	@Override
	public int generate(int max) {
		return this.generate(1, max);
	}

}

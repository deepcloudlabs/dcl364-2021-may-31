package com.example.lottery.service.business;

import java.security.SecureRandom;
import java.util.Random;

import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import javax.inject.Singleton;

import com.example.lottery.aop.Audit;
import com.example.lottery.aop.Profile;
import com.example.lottery.service.RandomNumberService;

@Named
@Singleton
@Alternative
@Audit
@Profile
public class SecureRandomNumberService implements RandomNumberService {

	private Random random = new SecureRandom();

	@Override
	public int generate(int min, int max) {
		System.err.println("SecureRandomNumberService::generate");
		return random.nextInt(max-min+1)+min ;
	}

	@Override
	public int generate(int max) {
		return generate(1, max);
	}

}

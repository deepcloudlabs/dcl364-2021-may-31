package com.example.lottery.service.business;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.lottery.aop.Audit;
import com.example.lottery.aop.Profile;
import com.example.lottery.service.QualityLevel;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.ServiceQuality;

@Service
@ServiceQuality(QualityLevel.SECURE)
@Audit
public class SecureRandomNumberService implements RandomNumberService {

	private Random random = new SecureRandom();

	@Override
	@Profile
	public int generate(int min, int max) {
		System.err.println("SecureRandomNumberService::generate");
		return random.nextInt(max - min + 1) + min;
	}

	@Override
	public int generate(int max) {
		return generate(1, max);
	}

}

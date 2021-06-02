package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.example.lottery.aop.Audit;
import com.example.lottery.aop.Profile;
import com.example.lottery.service.QualityLevel;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.ServiceQuality;

@Service
@ServiceQuality(QualityLevel.FAST)
@Audit
public class FastRandomNumberService implements RandomNumberService {

	@Override
	@Profile
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	@Override
	public int generate(int max) {
		return generate(1, max);
	}

}

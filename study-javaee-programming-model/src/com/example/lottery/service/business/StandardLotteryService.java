package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.example.lottery.aop.Audit;
import com.example.lottery.aop.Profile;
import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

// CDI (Java EE6) -> Spring
// CDI Component, Scope: Singleton, RequestScoped, SessionScope, ConversationScoped, ...
@Named
@Singleton
@Audit
public class StandardLotteryService implements LotteryService {
	@Inject
	private RandomNumberService randomNumberService;
	
	@Override
	@Profile
	public List<List<Integer>> draw(int max, int size, int column) {
		return IntStream.range(0, column)
				        .mapToObj( i -> this.draw(max,size))
				        .collect(Collectors.toList());
	}

	@Override
	public List<Integer> draw(int max, int size) {
		return IntStream.generate( () -> randomNumberService.generate(max))
				        .distinct()
				        .limit(size)
				        .sorted()
				        .boxed()
				        .parallel()
				        .collect(Collectors.toList());
	}

}

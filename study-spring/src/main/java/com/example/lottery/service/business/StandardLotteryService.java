package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.QualityLevel;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.ServiceQuality;

// Spring 
// Scope: Scope("singleton"), @RequestScope === @Scope("request") , @SessionScope === @Scope("session"), @Scope("prototype")
//@Service
//@Scope("singleton") // default scope
@Named
@Singleton
public class StandardLotteryService implements LotteryService {
	@Autowired
    @ServiceQuality(QualityLevel.SECURE)	
	private RandomNumberService randomNumberService;
	
	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		System.err.println(randomNumberService.getClass().getName());
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

package com.example.lottery.service.business;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

@Stateless
public class StandardLotteryService implements LotteryService {
	@Inject
	private RandomNumberService randomNumberService;
	
	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		return IntStream.range(0, column)
				        .mapToObj( i -> this.draw(max,size))
				        .collect(Collectors.toList())
				        .stream()
				        .map( future -> {
				            List<Integer> numbers= List.of();
							try {
								numbers = future.get();
							} catch (InterruptedException | ExecutionException e) { }
				            return numbers;
				        })
				        .collect(Collectors.toList());
	}

	@Override
	@Asynchronous
	public Future<List<Integer>> draw(int max, int size) {
		return new AsyncResult<>(IntStream.generate( () -> randomNumberService.generate(max))
				        .distinct()
				        .limit(size)
				        .sorted()
				        .boxed()
				        .parallel()
				        .collect(Collectors.toList())
				        );
	}

}

package com.example.lottery.service.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

// Functional Programming: i) Lambda Expression => anonymous class ii) Method Reference
// Function -> declare? 
public class StandardLotteryService implements LotteryService {
	// Dependency -> Low Coupling
	private RandomNumberService randomNumberService;
	
	// Constructor Injection
	public StandardLotteryService(RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
	}

	// Setter Injection
	public void setRandomNumberService(RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
	}

	@SuppressWarnings("unused")
	@Override
	public List<Integer> draw(int max, int size) {
		var numbers = new ArrayList<Integer>(size);
		while (numbers.size()<size) {
			var number = randomNumberService.generate(max);
			if (!numbers.contains(number))
				numbers.add(number);
		}
		// Comparator: Functional Interface -> SAM (Sing Abstract Method)
		final Integer x[] = new Integer[] {42};
		Comparator<Integer> numericOrderAsc = (o1, o2) -> {
			x[0]++;
			return o1-o2 ;
		}; 
		//numbers.sort( numericOrderAsc  );
		//numbers.sort((o1,o2)->Integer.compare(o1, o2));
		numbers.sort(Integer::compare);
		return numbers;
	}

	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		System.err.println(randomNumberService.getClass().getName());
		return IntStream.range(0, column)
				        .mapToObj( i -> this.functionalDraw(max,size))
				        .collect(Collectors.toList());
	}

	@Override
	public List<Integer> functionalDraw(int max, int size) {
		return IntStream.generate( () -> randomNumberService.generate(max))
				        .distinct()
				        .limit(size)
				        .sorted()
				        .boxed()
				        .parallel()
				        .collect(Collectors.toList());
	}

}

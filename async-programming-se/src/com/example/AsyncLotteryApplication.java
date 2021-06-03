package com.example;

import java.util.concurrent.TimeUnit;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.business.FastRandomNumberService;
import com.example.lottery.service.business.StandardLotteryService;

public class AsyncLotteryApplication {

	public static void main(String[] args) {
		FastRandomNumberService targetService = new FastRandomNumberService(); 
		LotteryService lotteryService = new StandardLotteryService(targetService); 
		for (var i=0;i<10;++i)
		    lotteryService.draw(60, 6)
		                  .thenAccept( numbers -> {
		                	  System.err.println(Thread.currentThread().getName()+"\t: "+numbers);  
		                  } ); // Async <--> Event-Driven
		System.err.println("After the for loop!");
		try { TimeUnit.SECONDS.sleep(5);}catch (Exception e) {}
		System.err.println("Application is done!");
	}

}

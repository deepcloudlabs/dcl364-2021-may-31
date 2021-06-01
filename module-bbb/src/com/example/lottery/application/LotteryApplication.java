package com.example.lottery.application;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.ServiceLoader;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberService;
import com.example.random.service.ServiceQuality;

public class LotteryApplication {

	public static void main(String[] args) throws Exception {
		ServiceLoader<RandomNumberService> services = ServiceLoader.load(RandomNumberService.class);
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/application.properties"));
		var serviceLevel = properties.getProperty("level");
		QualityLevel level = QualityLevel.valueOf(serviceLevel);
		var randomNumberService = services.stream()
				                          .peek(System.err::println)
				                          .filter( service -> service.get().getClass().getAnnotation(ServiceQuality.class).value().equals(level))
				                          .peek(System.err::println)
		                                  .findFirst();
	    if (randomNumberService.isPresent()) {
	    	StandardLotteryService lotteryService = new StandardLotteryService();
			lotteryService.setRandomNumberService(randomNumberService.get().get()); // setter injection
			lotteryService.draw(60, 6, 10)
			              .forEach(System.out::println);
	    }
		/*
		services.forEach( randomNumberService -> {
			StandardLotteryService lotteryService = new StandardLotteryService();
			lotteryService.setRandomNumberService(randomNumberService); // setter injection
			lotteryService.draw(60, 6, 10)
			              .forEach(System.out::println);
		});
		*/
	}

}

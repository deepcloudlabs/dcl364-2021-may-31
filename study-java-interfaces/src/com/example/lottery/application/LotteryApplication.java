package com.example.lottery.application;

import java.io.FileInputStream;
import java.util.Properties;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.business.StandardLotteryService;

public class LotteryApplication {

	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream("application.properties"));
		var serviceClassName = properties.getProperty("random.number.service");
		var clazz = Class.forName(serviceClassName);
		var randomNumberService = (RandomNumberService) clazz.newInstance();
		LotteryService lotteryService = new StandardLotteryService(randomNumberService);
		lotteryService.draw(60, 6, 10)
		              .forEach(System.out::println);
	}

}

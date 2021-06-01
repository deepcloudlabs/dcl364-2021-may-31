package com.example.lottery.application;

import java.io.FileInputStream;
import java.lang.reflect.Proxy;
import java.util.Properties;

import com.example.handler.AuditHandler;
import com.example.handler.ProfileHandler;
import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.business.FastRandomNumberService;
import com.example.lottery.service.business.StandardLotteryService;

public class LotteryApplication {

	public static void main(String[] args) throws Exception {
		FastRandomNumberService targetService = new FastRandomNumberService(); // target object
		var clazzLoader = targetService.getClass().getClassLoader();
		var interfaces = targetService.getClass().getInterfaces();
		RandomNumberService proxyService = (RandomNumberService)
				Proxy.newProxyInstance(clazzLoader, interfaces , new AuditHandler(targetService));
		targetService.setSelf(proxyService);
		RandomNumberService proxyService2 = (RandomNumberService)
				Proxy.newProxyInstance(clazzLoader, interfaces , new ProfileHandler(proxyService));
		
		LotteryService lotteryService = new StandardLotteryService(proxyService2); // client
		lotteryService.draw(60, 6, 10)
		              .forEach(System.out::println);
	}

}

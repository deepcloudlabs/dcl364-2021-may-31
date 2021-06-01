package com.example.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class ProfileHandler implements InvocationHandler {
	private Object target;
	
	public ProfileHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		var methodName = method.getName();
		var start = System.nanoTime();
		var result = method.invoke(target, args);
		var stop = System.nanoTime();
		System.out.println(methodName + " runs "+ (stop-start) + " ns.");		
		return result;
	}

}

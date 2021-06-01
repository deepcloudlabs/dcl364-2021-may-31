package com.example.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

public class AuditHandler implements InvocationHandler {
	private Object target;
	
	public AuditHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		var methodName = method.getName();
		var now = new Date();
		System.out.println(methodName  + " is invoked at "+now+" with parameters "+Arrays.toString(args));
		var result = method.invoke(target, args);
		System.out.println(methodName + " returns "+ result);		
		return result;
	}

}

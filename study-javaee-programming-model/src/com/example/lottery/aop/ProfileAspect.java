package com.example.lottery.aop;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Profile
public class ProfileAspect {

	@AroundInvoke
	public Object audit(InvocationContext ic) throws Throwable {
		var methodName = ic.getMethod().getName();
		var start = System.nanoTime();
		var result = ic.proceed();
		var stop = System.nanoTime();
		System.out.println(methodName + " runs "+ (stop-start) + " ns.");		
		return result;	
	}
}

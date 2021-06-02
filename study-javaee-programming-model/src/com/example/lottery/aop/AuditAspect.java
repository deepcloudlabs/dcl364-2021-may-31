package com.example.lottery.aop;

import java.util.Arrays;
import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Audit
public class AuditAspect {

	@AroundInvoke
	public Object audit(InvocationContext ic) throws Throwable {
		var methodName = ic.getMethod().getName();
		var now = new Date();
		System.out.println(methodName  + " is invoked at "+now+" with parameters "+Arrays.toString(ic.getParameters()));
		var result = ic.proceed();
		System.out.println(methodName + " returns "+ result);		
		return result;		
	}
}

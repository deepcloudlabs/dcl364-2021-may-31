package com.example.lottery.aop;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditAspect {

	@AfterReturning
	@AfterThrowing
	@After("")
	@Before("")
	@Around("@annotation(audit)")
	public Object audit(ProceedingJoinPoint pjp,Audit audit) throws Throwable {
		var methodName = pjp.getSignature().getName();
		var now = new Date();
		System.out.println(methodName  + " is invoked at "+now+" with parameters "+Arrays.toString(pjp.getArgs()));
		var result = pjp.proceed();
		System.out.println(methodName + " returns "+ result);		
		return result;		
	}
	
	/*
	@Around("@target(audit)")
	public Object classAudit(ProceedingJoinPoint pjp,Audit audit) throws Throwable {
		var methodName = pjp.getSignature().getName();
		var now = new Date();
		System.out.println(methodName  + " is invoked at "+now+" with parameters "+Arrays.toString(pjp.getArgs()));
		var result = pjp.proceed();
		System.out.println(methodName + " returns "+ result);		
		return result;
	}
	*/
}

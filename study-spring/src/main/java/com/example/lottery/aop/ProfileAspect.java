package com.example.lottery.aop;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProfileAspect {
//	@Before
//	@AfterReturning
//	@AfterThrowing
//	@After
	@Around("classProfilerAnnotated() " + "|| methodProfilerAnnotated()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		TimeUnit tu = TimeUnit.NANOSECONDS;
		Class<?> clazz = pjp.getTarget().getClass();
		if (clazz.isAnnotationPresent(Profile.class)) {
			tu = clazz.getAnnotation(Profile.class).unit();
		}
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = clazz.getDeclaredMethod(signature.getName(), signature.getParameterTypes());
		if (method.isAnnotationPresent(Profile.class))
			tu = method.getAnnotation(Profile.class).unit();
		long start = System.nanoTime();
		Object result = pjp.proceed();
		long stop = System.nanoTime();
		System.out.println(String.format("%s runs %d %s", signature.getName(),
				tu.convert((stop - start), TimeUnit.NANOSECONDS), tu.name().toLowerCase()));
		return result;
	}

	@Pointcut("@annotation(com.example.lottery.aop.Profile)")
	public void classProfilerAnnotated() {
	}

	@Pointcut("within(@com.example.lottery.aop.Profile *)")
	public void methodProfilerAnnotated() {
	}
}
package com.example.demo.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DaoCommon {
	
	@Pointcut("execution(public * com.example.demo.dao..*(..))")
	public void daoCommon() {}
	
	@Before("daoCommon()")
	public void pro(JoinPoint joinpoint) {
		String methodName1 = joinpoint.getSignature().toLongString();
		String methodName2 = joinpoint.getSignature().toShortString();
		System.out.println(methodName1);
		System.out.println(methodName2);
		System.out.println("DAO가 처리되기전에 동작하는 공통기능입니다.");
		System.out.println("------------------------------------------");
	}
}
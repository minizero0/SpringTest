package com.example.demo.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DaoCommon {
	
	@Pointcut("execution(public * com.example.demo.dao..*(..))")
	public void daoCommon() {}
	
	@AfterReturning("daoCommon()")
	public void afterReturning(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println();
		System.out.println("타깃 메소드("+methodName+")가 정상 완료");
	}
	
	
//	@Around("daoCommon()")
//	public Object around(ProceedingJoinPoint joinPoint) {
//		Object obj = null;
//		String methodName = joinPoint.getSignature().getName();
//		System.out.println(methodName+"가 동작하기 전입니다.");
//		long start = System.currentTimeMillis();
//		try {
//			obj = joinPoint.proceed();
//		}catch (Throwable e) {
//			e.printStackTrace();
//		}
//		
//		long end = System.currentTimeMillis();
//		System.out.println(methodName+"가 완료되었습니다.");
//		System.out.println("걸린시간:"+(end-start));
//		return obj;
//	}
	
	
	/*
	@Before("daoCommon()")
	public void pro(JoinPoint joinpoint) {
		String methodName1 = joinpoint.getSignature().toLongString();
		String methodName2 = joinpoint.getSignature().toShortString();
		System.out.println(methodName1);
		System.out.println(methodName2);
		
		Object targetObj = joinpoint.getTarget();
		System.out.println("타깃:"+targetObj);
		Object []args = joinpoint.getArgs();
		System.out.println("매개변수 목록");
		if(args != null) {
			for(Object obj : args) {
				System.out.println(obj);
			}
		}
		
		System.out.println("DAO가 처리되기전에 동작하는 공통기능입니다.");
		System.out.println("------------------------------------------");
	}
	*/
	
	
}
package com.example.demo.common;

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
	public void pro() {
		System.out.println("DAO가 처리되기전에 동작하는 공통기능입니다.");
	}
}
package com.example.demo.common;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
@Aspect
public class ControllerCommon {

	@Around("execution(public * com.example.demo.dao..*(..))")
	public Object pro(ProceedingJoinPoint joinPoint) {
		Object []args = joinPoint.getArgs();
		HttpServletRequest request = (HttpServletRequest)args[0];
		String ip = request.getRemoteAddr();
		Date today = new Date();
		int year = today.getYear()+1900;
		int month = today.getMonth()+1;
		int day = today.getDate();
		int hh = today.getHours();
		int mm = today.getMinutes();
		int ss = today.getSeconds();
		String time = year +"년 "+month+"월 "+day+"일 "+hh+":"+mm+":"+ss;
		long start = System.currentTimeMillis();
		
		Object ret = null;
		try {
			ret = joinPoint.proceed();
			
		}catch (Throwable e) {
			System.out.println("예외발생"+e.getMessage());
		}
		long end = System.currentTimeMillis();
		
		return ret;
	}
}

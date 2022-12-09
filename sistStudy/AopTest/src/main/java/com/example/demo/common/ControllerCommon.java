package com.example.demo.common;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.LogDAO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Component
@Aspect
@Setter
public class ControllerCommon {
	
	@Autowired
	private LogDAO dao;
	
	@Pointcut("execution(public * com.example.demo.controller..*(..))")
	public void commonController() {}; 
	
	
	@Around("execution(public * com.example.demo.controller..*(..))")
	public Object pro(ProceedingJoinPoint joinPoint) {
		Object []args = joinPoint.getArgs();
		HttpServletRequest request = (HttpServletRequest)args[0];
		String uri = request.getRequestURI();
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
		
		String line = uri+"/"+ip+"/"+(end-start)+"/"+time;
		System.out.println(uri+"/"+ip+"/"+(end-start)+"/"+time);
		
		try {
			FileWriter fw = new FileWriter("/Users/mini0/Desktop/sist/log.txt", true);
			fw.write(line);
			fw.close();
		} catch (Exception e) {
	            e.getStackTrace();
		}
		int no = dao.nextNo();
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("log", line);
		
		dao.insert(map);
		
		
		return ret;
	}
}

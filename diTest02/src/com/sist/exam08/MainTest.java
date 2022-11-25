package com.sist.exam08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/sist/exam08/beans.xml");
		ProtocolHandler ph = (ProtocolHandler)context.getBean("handler");
		ph.pro();
	}
}

package com.sist.exam03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp2 {
	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("com/sist/exam02/beans.xml");
		SystemMonitor sm = (SystemMonitor)context.getBean("sm");
		sm.monitor();
	}
}

package com.sist.exam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("com/sist/exam01/beans.xml");
		Member m = (Member)context.getBean("ob");
		m.sayHello();
	}
}

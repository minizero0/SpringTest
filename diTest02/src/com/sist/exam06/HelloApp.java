package com.sist.exam06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("com/sist/exam06/beans.xml");
		WriteArticleService ws = (WriteArticleService)context.getBean("ws");
		ws.insert();
	}
}

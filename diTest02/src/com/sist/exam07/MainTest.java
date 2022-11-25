package com.sist.exam07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/sist/exam07/beans.xml");
		MemberDAO dao = (MemberDAO)context.getBean("md");
		dao.insert();
	}
}

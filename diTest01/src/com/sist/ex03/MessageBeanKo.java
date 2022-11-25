package com.sist.ex03;

public class MessageBeanKo implements MessageBean {

	@Override
	public void sayHello(String name) {
		System.out.println("Hello,"+name+"님!");
	}
}

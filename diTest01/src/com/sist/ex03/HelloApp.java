package com.sist.ex03;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class HelloApp {
	
	public static void main(String[] args) {
		
		Resource resource = new FileSystemResource("beans.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		MessageBean bean = (MessageBean)factory.getBean("mb");
		
		bean.sayHello("Spring");
		
	}
}

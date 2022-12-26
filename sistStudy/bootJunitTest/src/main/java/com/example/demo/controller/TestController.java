package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@PostMapping("/hello")
	public String list(String name, int age, String sex) {
		System.out.println("name:"+name);
		System.out.println("age:"+age);
		System.out.println("sex:"+sex);
		return name+age;
	}
}

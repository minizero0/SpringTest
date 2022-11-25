package com.sist.exam07;

public class Member {
	private String name;
	private int age;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void info() {
		System.out.println("이름:"+name+",나이:"+age);
	}
}

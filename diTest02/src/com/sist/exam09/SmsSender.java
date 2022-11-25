package com.sist.exam09;

public class SmsSender {
	private String from;
	private String to;
	
	
	public void setFrom(String from) {
		this.from = from;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public void send() {
		System.out.println(from+"으로 부터 " + to + "에게 메세지를 전송하였습니다.");
	}
	
	
	
}

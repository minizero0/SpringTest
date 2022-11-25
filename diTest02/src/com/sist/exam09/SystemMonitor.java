package com.sist.exam09;

public class SystemMonitor {
	private int periodTime;
	private SmsSender sender;

	
	public void setPeriodTime(int periodTime) {
		this.periodTime = periodTime;
	}


	public void setSender(SmsSender sender) {
		this.sender = sender;
	}


	public void monitor() {
		System.out.println(periodTime + "분 간격으로 ");
		sender.send();
	}
	

}

package com.example.demo.util;

import javax.tools.JavaFileManager.Location;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@EnableScheduling
public class SistUtil {
	
	//초 분 시간 일 월 요일 연도
	@Scheduled(cron = "0 25 12 * * ?")
	public void pro() {
		System.out.println("메일전송");
	}
}

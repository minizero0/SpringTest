package com.example.demo;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping("/hello.do")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", getGreeting());
		mav.setViewName("hello");
		
		return mav;
	}
	
	public String getGreeting() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if(hour >= 6 && hour <= 10) {
			return "좋은 아침";
		}
		else if (hour >= 12 && hour <= 15) {
			return "점심은 먹었어요?";
		}
		else if (hour >= 16 && hour <= 20) {
			return "잘자요";
		}
		return "안녕하세요";
	}
}

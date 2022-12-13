package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping("/index.do")
	public ModelAndView index() {
		return new ModelAndView("/index");
	}
}

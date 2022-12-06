package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("logout")
public class LogoutMemberController {
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form(HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		session.invalidate();
		return mav;
	}
}

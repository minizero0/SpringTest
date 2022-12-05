package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;

@Controller
@RequestMapping("/deleteBoard")
public class DeleteBoardController {
	
	@Autowired
	private BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void form(int no, Model model) {
		model.addAttribute("no",no);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit() {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		return mav;
	}
	
	
}

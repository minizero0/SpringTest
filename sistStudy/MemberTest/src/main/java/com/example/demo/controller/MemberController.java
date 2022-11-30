package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO dao;

	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/listMember")
	public ModelAndView listMember() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", dao.findAll());
		return mav;
	}
	
	@RequestMapping("/detailMember")
	public ModelAndView detailMember(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("m", dao.findById(no));
		return mav;
	}

}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;

import lombok.Setter;

@Controller
@Setter
public class AdminController {
	@Autowired
	private MemberDAO memberDAO;
	
	@GetMapping("/admin/index")
	public void index() {
		
	}
	
	@GetMapping("/admin/member/list")
	public ModelAndView adminForm() {
		ModelAndView mav = new ModelAndView("/admin/member/list");
		mav.addObject("list", memberDAO.findAll());
		return mav;
	}
	
	
	@GetMapping("/admin/member/adminJoin")
	public void adminJoin() {
		
	}
}

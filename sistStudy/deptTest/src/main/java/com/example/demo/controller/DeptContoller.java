package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.DeptDAO;

@Controller
public class DeptContoller {

	@Autowired
	private DeptDAO dao;
	
	public void setDao(DeptDAO dao) {
		this.dao = dao;
	}

	@RequestMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.DeptDAO;
import com.example.demo.vo.DeptVO;

import lombok.Setter;

@Controller
@Setter
public class DeptController {

	@Autowired
	private DeptDAO dao;
	
	@RequestMapping("/dept/list")
	public void list(Model model) {
		model.addAttribute("list", dao.findAll());
	}
	
	@GetMapping("/dept/detail/{dno}")
	public ModelAndView detail(@PathVariable int dno) {
		System.out.println("부서번호:"+dno);
		DeptVO d = dao.findByNo(dno);
		ModelAndView mav = new ModelAndView("/dept/detail");
		mav.addObject("d", d);
		return mav;
	}
}

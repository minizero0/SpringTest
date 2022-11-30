package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.vo.CustomerVO;

@Controller
public class CustomerController {
	@Autowired
	private CustomerDAO dao;

	public void setDao(CustomerDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", dao.findAll());
		return mav;
	}
	
	@RequestMapping("/detailCustomer")
	public ModelAndView detail(int custid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("b", dao.findById(custid));
		return mav;
	}
	
	//@RequestMapping(value = "/insertCustomer", method = RequestMethod.GET)
	@GetMapping("/insertCustomer")
	public void insertForm() {
	}
	
	//@RequestMapping(value = "/insertCustomer", method = RequestMethod.POST)
	@PostMapping("/insertCustomer")
	public ModelAndView insertSubmit(CustomerVO c) {
		ModelAndView mav = new ModelAndView();
		int re = dao.insert(c);
		System.out.println(re);
		return mav;
	}
	
	@GetMapping("/updateCustomer")
	public int updateGet(int custid) {
		return custid;
		
	}
	@PostMapping("updateCustomer")
	public ModelAndView updateSubmit(CustomerVO c) {
		ModelAndView mav = new ModelAndView();
		int re = dao.update(c);
		System.out.println(re);
		return mav;
	}
	
	
	
	
}

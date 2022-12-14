package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.CustomerService;

import lombok.Setter;

@Controller
@Setter
public class CustomerController {
	
	@Autowired
	private CustomerService cs;
	
	@GetMapping("/customer/list")
	public void findAll(Model model) {
		model.addAttribute("list", cs.findAll());
	}
	

}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.DeptDAO;

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
}

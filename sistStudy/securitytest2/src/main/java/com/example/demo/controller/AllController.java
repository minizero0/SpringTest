package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AllController {

	@GetMapping("/all/menu1")
	public String menu1() {
		return "/all/menu1";
	}
	@GetMapping("/all/menu2")
	public void menu2() {
	}
}

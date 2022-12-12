package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	@GetMapping("/admin/menu1")
	public String admin1() {
		return "관리자 메뉴1";
	}
	
	@GetMapping("/admin/menu2")
	public String admin2() {
		return "관리자 메뉴2";
	}
}

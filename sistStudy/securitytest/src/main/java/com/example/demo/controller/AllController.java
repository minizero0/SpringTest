package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllController {

	@GetMapping("/all/menu1")
	public String menu1() {
		return "메뉴1입니다.";
	}
}

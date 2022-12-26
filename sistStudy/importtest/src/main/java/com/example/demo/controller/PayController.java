package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {
	
	@GetMapping("/payok")
	public String payok(String imp_uid, String merchant_uid, String paid_amount, String apply_num) {
		System.out.println(imp_uid);
		System.out.println(merchant_uid);
		System.out.println(paid_amount);
		System.out.println(apply_num);
		return "ok";
	}
	
}

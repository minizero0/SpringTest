package com.example.demo.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.youiwe.webservice.BitSms;

@RestController
public class MessageController {
	
	@GetMapping("/sendMsg")
	public String sendMessage() {
		String from = "01025598279";
		String to = "01041418894";
		String msg = "밥 뭇나?";
		
		BitSms sms = new BitSms();
		sms.sendMsg(from, to, msg);
		return "OK";
	}
	
	@GetMapping("/CheckMsg")
	public String checkMsg(String phone) {
		String from = "01025598279";
		
		String code = "";
		Random r = new Random();
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		BitSms sms = new BitSms();
		sms.sendMsg(from, phone, code);
		return code;
	}
}

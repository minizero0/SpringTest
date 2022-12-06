package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
	
	@Autowired
	private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@RequestMapping("/mail")
	@ResponseBody
	public String mail() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("dudals1069@gmail.com");
		mailMessage.setTo("ehwjrdufvnd@naver.com");
		mailMessage.setSubject("메일 보내기 연습");
		mailMessage.setText("메일을 보냅니다!");
		try {
			mailSender.send(mailMessage);
		}catch (Exception e) {
			System.out.println("메일에러:"+e.getMessage());
		}
		return "OK";
	}
	
	@RequestMapping("/sendCode")
	@ResponseBody
	public String sendCode(String email) {
		System.out.println("email:"+email);
		String code = "";
		Random r = new Random();
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		return code;
	}
}

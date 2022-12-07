package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.youiwe.webservice.BitSms;

@RestController
public class AuthentificationController {
	
	@Autowired
	private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Autowired
	private BitSms sms;
	public void setSms(BitSms sms) {
		this.sms = sms;
	}
	

	@GetMapping("/sendAuthCode")
	public String auth(String auth_type, String to) {
		String code = "";
		Random r = new Random();
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		
		if(auth_type.equals("email")) {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom("dudals1069@gmail.com");
			mailMessage.setTo(to);
			mailMessage.setSubject("인증코드 전송");
			mailMessage.setText(code);
			try {
				mailSender.send(mailMessage);
			}catch (Exception e) {
				System.out.println("메일에러:"+e.getMessage());
			}
		}else {
			sms.sendMsg("01025598279", to, code);
		}
		return code;
	}
}

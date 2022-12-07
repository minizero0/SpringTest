package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

import kr.co.youiwe.webservice.BitSms;

@RestController
public class EmpController {
	
	@Autowired
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Autowired
	private EmpDAO dao;
	

	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}

	@GetMapping("/sendMail")
	public String mailSend() {
		List<EmpVO> list = dao.findEmpAll();
		for(EmpVO e : list) {
			if(e.getEname().equals("조영민")) {
				System.out.println(e.getEmail());
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setFrom("dudals1069@gmail.com");
				mailMessage.setTo(e.getEmail());
				mailMessage.setSubject("급여명세서");
				mailMessage.setText(e.getEname() + "님의 올해 급여명세서 입니다.\n salary :" + e.getSalary() );
				try {
					mailSender.send(mailMessage);
				}catch (Exception a) {
					System.out.println("메일에러:"+a.getMessage());
				}
			}
		}
		return "OK";
	}
}

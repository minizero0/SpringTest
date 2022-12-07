package com.example.demo.util;

import java.util.List;

import javax.tools.JavaFileManager.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

@Component
@EnableScheduling
public class SistMailScheduling {
	
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
	
	
	//초 분 시간 일 월 요일 연도
	@Scheduled(cron = "0 41 12 * * ?")
	public void pro() {
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
		System.out.println("메일전송");
	}
}

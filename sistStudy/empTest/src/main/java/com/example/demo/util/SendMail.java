package com.example.demo.util;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

import lombok.Setter;

@RestController
@Component
@EnableScheduling
@Setter
public class SendMail {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmpDAO dao;
	
	//초 분 시간 일 월 요일 연도
	@Scheduled(cron = "0 39 17 * * ?")
	public String pro() {
		List<EmpVO> list = dao.findAll();
		for(EmpVO e : list) {
			if(e.getEname().equals("조영민")) {
				mailSender.send(new MimeMessagePreparator() {
					
					@Override
					public void prepare(MimeMessage mimeMessage) throws Exception {
						// TODO Auto-generated method stub
						String str = "<h2>급여 : "+e.getSalary()+"</h2>";
						str += "고생하셨습니다.";
						MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
						helper.setFrom("dudals1069@gmail.com");
						helper.setTo(e.getEmail());
						helper.setSubject(e.getEname() + "님 급여명세서");
						helper.setText(str, true);
						helper.addAttachment("money.txt", new ClassPathResource("doc/money.txt"));
					}
				});
				
			}
		}
		return "OK";	
	}
	
	

}

package com.example.demo.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HtmlMailController {

	@Autowired
	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}


	@GetMapping("/sendMailHtml")
	public String sendMailHtml() {
		mailSender.send(new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				String str = "<h2>회원가입 성공</h2>";
				str += "<img src ='cid:ball'/>";
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				helper.setFrom("dudals1069@gmail.com");
				helper.setTo("ehwjrdufvnd@naver.com");
				helper.setSubject("html형태의 이메일");
				helper.setText(str, true);
				
				helper.addInline("ball", new ClassPathResource("img/ball1.jpg"));
				helper.addAttachment("hello.txt", new ClassPathResource("doc/hello.txt"));
			}
		});
		return "OK";
	}

}

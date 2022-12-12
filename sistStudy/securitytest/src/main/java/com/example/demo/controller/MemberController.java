package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

	@GetMapping("/login")
	public void login() {
	}
	
	@GetMapping("/service1")
	public void service1(HttpSession session) {
		//인증된(로그인한) 회원의 정보를 가져오기 위하여
		//시큐리티의 인증객체가 필요.
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		
		//이 인증객체를 통해서 인증된(로그인된) User객체를 받아 온다.
		User user = (User)authentication.getPrincipal();
		
		//이 인증된 User를 통해서 로그인된 회원의 아이디를 가져온다.
		
		String id = user.getUsername();
		//아이디 정보를 세션에 상태유지 한다.
		//만약, id뿐 아니라 로그인한 회원의 다른정보도 필요하다면 dao를 통해 회원 정보를 가져와서 상태유지
		
		session.setAttribute("id", id);
	}
	
	
	@GetMapping("/service2")
	@ResponseBody
	public String service2() {
		return "서비스2입니다.";
	}
}
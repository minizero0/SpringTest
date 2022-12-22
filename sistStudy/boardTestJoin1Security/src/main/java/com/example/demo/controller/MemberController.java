package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private MemberService ms;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/member/join")
	public void joinForm() {
		
	}
	
	@PostMapping("/member/join")
	public ModelAndView joinSubmit(Member m) {
		//String encPwd = passwordEncoder.encode(m.getPwd());
		//m.setPwd(encPwd);
		ModelAndView mav = new ModelAndView("redirect:/member/login");
		m.setPwd(passwordEncoder.encode(m.getPwd()));
		
//		Optional<Member> obj = memberDAO.findById(m.getId());
//		if(obj.isEmpty()) {
//			mav.addObject("msg", "회원가입에 실패하였습니다.");
//			mav.setViewName("error");
//		}
		try {
			memberDAO.save(m);
		}catch (Exception e) {
			mav.addObject("msg", "회원가입에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/member/login")
	public void loginForm(Model model) {
		memberDAO.save(new Member("kim", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("kim"), "kim", "user",null));
		memberDAO.save(new Member("messi", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("messi"), "messi", "user",null));
	}
	
//	@PostMapping("/member/login")
//	public ModelAndView loginSubmit(String id, String pwd, HttpSession session) {
//		ModelAndView mav = new ModelAndView("redirect:/board/list/1/all");
//		System.out.println(id);
//		Member m = ms.login(id, pwd);
//		if(m != null) {
//			session.setAttribute("id", id);
//		}else {
//			mav.setViewName("redirect:/member/login");
//		}
//		
//		return mav;
//	}
}

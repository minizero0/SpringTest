package com.example.demo.controller;

import java.beans.JavaBean;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("logIn")
public class LoginMemberController {
	@Autowired
	private MemberDAO dao;

	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void form() {
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(String id, String pwd,HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		MemberVO m = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd", pwd);
		m = dao.logIn(map);
		
		if(m == null) {
			mav.addObject("msg", "로그인 실패");
			mav.setViewName("error");
		}else {
			String name = m.getName();
			session.setAttribute("loginUser", m);
		}
		
		return mav;
		
	}
	
}

package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.MemberVO;

@Controller
public class MemberController {
	
	@RequestMapping("/list.do")
	@ResponseBody
	public List<MemberVO> list(){
		List<MemberVO> list = new ArrayList<>();
		list.add(new MemberVO("이순신", 20, "전라도"));
		list.add(new MemberVO("이동경", 23, "동경"));
		list.add(new MemberVO("메시", 20, "아르헨티나"));
		return list;
	}
	
	@RequestMapping("/member.do")
	@ResponseBody
	public MemberVO member() {
		MemberVO b = new MemberVO("홍길동", 20, "서울");
		return b;
				
	}
	
}

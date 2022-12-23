package com.example.demo.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class AdminController {
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/admin/index")
	public void index() {
		
	}
	
	@GetMapping("/admin/member/list")
	public ModelAndView adminForm() {
		ModelAndView mav = new ModelAndView("/admin/member/list");
		mav.addObject("list", memberDAO.findAll());
		return mav;
	}
	
	@GetMapping("/admin/member/update/{id}")
	public ModelAndView update(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("/admin/member/update");
		try {
			Member m = memberDAO.findById(id).get();
			mav.addObject("m", m);
		}catch (Exception e) {
			mav.addObject("msg", "수정 실패.");
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	@PostMapping("/admin/member/update")
	public ModelAndView update(Member m) {
		ModelAndView mav = new ModelAndView("redirect:/admin/member/list");
		System.out.println(m);
		m.setPwd(passwordEncoder.encode(m.getPwd()));
		try {
			memberDAO.update(m);
		}catch (Exception e) {
			mav.addObject("msg", "회원수정에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/admin/member/delete/{id}")
	public ModelAndView delete(@PathVariable String id, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/images");
		ModelAndView mav = new ModelAndView("redirect:/admin/member/list");
		
		//삭제하기 전에 이 계정이 등록한 게시물의 파일이름들을 리스트에 담음
		List<String> fname_list = boardDAO.findFnameByid(id);
		for(String fname:fname_list) {
			System.out.println(fname);
		}
		
		memberDAO.deleteById(id);
		
		Optional<Member> obj = memberDAO.findById(id);
		if(obj.isEmpty()) {
			for (String fname:fname_list) {
				if(fname!=null&&!fname.equals("")) {
					File file = new File(path + "/" + fname);
					file.delete();
				}
			}
		}
		return mav;
	}
	
	
	@GetMapping("/admin/member/adminJoin")
	public void adminJoin() {
		
	}
}

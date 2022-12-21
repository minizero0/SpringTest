package java.com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.com.example.demo.entity.Member;
import java.com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	private MemberService ms;
	
	@GetMapping("/member/login")
	public void loginForm(Model model) {
		
	}
	
	@PostMapping("/member/login")
	public ModelAndView loginSubmit(String id, String pwd, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/board/list/1");
		Member m = ms.login(id, pwd);
		if(m != null) {
			session.setAttribute("id", id);
		}else {
			mav.setViewName("redirect:/member/login");
		}
		
		return mav;
	}
}

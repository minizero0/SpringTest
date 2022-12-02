package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("insertBoard")
public class InsertBoardController {
	
	@RequestMapping(method = RequestMethod.GET)
	public void form() {
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(BoardVO b, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		request.getServletContext().getRealPath("images");
		String fname = b.getUploadFile().getOriginalFilename();
		return mav;
	}
}

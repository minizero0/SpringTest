package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@GetMapping("/board/insert")
	public void insertForm(Model model) {
		model.addAttribute("no", bs.getNextNo());
	}
	
	@PostMapping("/board/insert")
	public ModelAndView insertSubmit(Board b) {
		ModelAndView mav = new ModelAndView("redirect:/board/list");
		bs.insert(b);
		return mav;
	}
	
	@GetMapping("/board/list")
	public void list(Model model) {
		model.addAttribute("list", bs.findAll());
	}
	
	
	
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.BoardDAO;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class BoardController {

	@Autowired
	private BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping("/listBoard")
	public void listBoard(Model model) {
		model.addAttribute("list", dao.findAll());
	}
	
	@GetMapping("/detailBoard")
	public void detail(Model model, int no) {
		model.addAttribute("b", dao.findByNo(no));
	}
	
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.BoardDAO;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class BoardController {
	private int totalPage = 1;
	private int pageSIZE = 10; 
	private int totalRecord = 0;
	
	@Autowired
	private BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping("/listBoard")
	public void listBoard(Model model) {
		
		totalRecord = dao.getTotal();
		totalPage = (int)Math.ceil((double)totalRecord / pageSIZE);
		model.addAttribute("total", totalPage);
		model.addAttribute("list", dao.findAll());
		
	}
	
	@GetMapping("/detailBoard")
	public void detail(Model model, int no) {
		model.addAttribute("b", dao.findByNo(no));
	}
	
}

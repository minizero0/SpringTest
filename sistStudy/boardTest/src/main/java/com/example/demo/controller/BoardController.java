package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.BoardDAO;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class BoardController {
	private int pageSIZE = 3;	//한 화면에 보여줄 레코드 수
	private int totalRecord = 0;
	private int totalPage = 1;
	
	private int pageGROUP = 5;	//한 화면에 보여줄 페이지 수
	@Autowired
	private BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping("/listBoard")
	public void listBoard(Model model, 
			@RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM) {
		totalRecord = dao.getTotal();
		totalPage = (int)Math.ceil((double)totalRecord / pageSIZE);
		int start = (pageNUM-1) * pageSIZE +1;
		int end = start + pageSIZE - 1;
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		model.addAttribute("total", totalPage);
		model.addAttribute("list", dao.findAll(map));
		
		//만약, 현재페이지가 1,2,3,4,5 라면  start 1
		//만약, 현재페이지가 6,7,8,9,10 라면  start 6
		//startPage와 endPage를 구하여 출력
		int startPage = (pageNUM-1)/pageGROUP*pageGROUP+1;
		int endPage = startPage + pageGROUP -1;
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		
	}
	
	@GetMapping("/detailBoard")
	public void detail(Model model, int no) {
		model.addAttribute("b", dao.findByNo(no));
	}
	
}

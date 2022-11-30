package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsDAO dao;

	public void setDao(GoodsDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/listGoods")
	public ModelAndView listGoods() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", dao.findAll());
		return mav;
	}
	
	@RequestMapping("/detailGoods")
	public ModelAndView detailGoods(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", dao.findByNo(no));
		return mav;
	}
	
	@GetMapping("/insertGoods")
	public void insertForm() {
	}
	@PostMapping("/insertGoods")
	public ModelAndView insertSubmit(GoodsVO g) {
		ModelAndView mav = new ModelAndView();
		int re = dao.insert(g);
		if(re > 0) {
			mav.setViewName("redirect:/listGoods");
		}
		return mav;
	}
}

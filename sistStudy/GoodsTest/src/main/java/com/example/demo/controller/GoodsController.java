package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

@Controller
public class GoodsController {
	
	int pageSIZE = 5;
	int totalRecord = 0;
	int totalPage = 1;
	
	@Autowired
	private GoodsDAO dao;

	public void setDao(GoodsDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/listGoods")
	public ModelAndView listGoods(@RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM) {
		totalRecord = dao.getTotal();
		totalPage = (int)Math.ceil((double)totalRecord / pageSIZE);
		HashMap<String, Integer> map = new HashMap<>();
		int end = pageNUM * pageSIZE;
		int start = end - pageSIZE + 1;
		/*
		 * int start = (pageNUM-1)*pageSZIE+1;
		 * int end = start + pageSIZE - 1;
		 * */
		map.put("start", start); 
		map.put("end", end);
		ModelAndView mav = new ModelAndView();
		mav.addObject("totalPage", totalPage);
		mav.addObject("list", dao.findAll(map));
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
		}else if(re <= 0) {
			mav.addObject("msg", "상품 등록에 실패했습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/updateGoods")
	public ModelAndView updateForm(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("g", dao.findByNo(no));
		return mav;
	}
	@PostMapping("/updateGoods")
	public ModelAndView updateSubmit(GoodsVO g) {
		ModelAndView mav = new ModelAndView();
		int re = dao.update(g);
		if(re>0) {
			mav.setViewName("redirect:/detailGoods?no="+g.getNo());
		}else if (re <= 0) {
			mav.addObject("msg", "상품 수정에 실패했습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/deleteGoods")
	public ModelAndView deleteGoods(int no) {
		ModelAndView mav = new ModelAndView();
		int re = dao.delete(no);
		if(re>0) {
			mav.setViewName("redirect:/listGoods");
		}else if (re <= 0) {
			mav.addObject("msg", "상품 삭제에 실패했습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
	
}

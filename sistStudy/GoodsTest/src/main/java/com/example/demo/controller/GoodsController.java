package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	public ModelAndView listGoods(
			@RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM, 
			String column,
			HttpSession session,
			String keyword,
			String cate
			) {
		System.out.println(cate);
		if(session.getAttribute("column") != null && column == null) {
			column = (String)session.getAttribute("column");
		}
		if(session.getAttribute("keyword") != null && (keyword == null || keyword.equals(""))) {
			keyword = (String)session.getAttribute("keyword");
		}
		if(session.getAttribute("cate") != null && (cate == null || cate.equals(""))) {
			cate = (String)session.getAttribute("cate");
		}
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("column", column);
		map.put("keyword", keyword);
		map.put("cate", cate);
		totalRecord = dao.getTotal(map);
		totalPage = (int)Math.ceil((double)totalRecord / pageSIZE);
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
		mav.addObject("pageNUM", pageNUM);
		mav.addObject("list", dao.findAll(map));
		session.setAttribute("column", column);
		session.setAttribute("keyword", keyword);
		session.setAttribute("cate", cate);
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
	public ModelAndView insertSubmit(GoodsVO g, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MultipartFile uploadFile = g.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		String path = request.getServletContext().getRealPath("images");
		System.out.println("path:"+path);
		try {
			byte[]data = uploadFile.getBytes();
			FileOutputStream fos = new FileOutputStream(path+"/"+fname);
			fos.write(data);
			fos.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		g.setFname(fname);
		
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

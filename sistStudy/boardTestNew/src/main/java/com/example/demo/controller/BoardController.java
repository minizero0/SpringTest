package com.example.demo.controller;

import java.io.FileOutputStream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@GetMapping("/board/insert")
	public void insertForm(@RequestParam(value = "no", defaultValue="0") int no ,Model model) {
		System.out.println(no);
		if(no>0) {
			model.addAttribute("no", no);
		}else {
			model.addAttribute("no", bs.getNextNo());
		}
	}
	
	@PostMapping("/board/insert")
	public ModelAndView insertSubmit(Board b, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/board/list");
		String ip = request.getRemoteAddr();
		b.setIp(ip);
		
		String path = request.getServletContext().getRealPath("images");
		MultipartFile uploadFile = b.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		if(fname != null && !fname.equals("")) {
			try {
				byte[] data = uploadFile.getBytes();
				System.out.println("path:"+path);
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				fos.write(data);
				fos.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}else {
			fname = "";
		}
		b.setFname(fname);
		
		int no = bs.getNextNo();
		int p_no = b.getNo();
		int d = bs.findByNo(p_no);
		int b_ref = 0;
		int b_level = 0;
		int b_step = 0;
		if(d>0) {
			Board p = bs.findById(p_no);
			b_ref = p_no;
			b_level = p.getB_level();
			b_step = p.getB_step();
			bs.updateStep(b_ref, b_step);
			b_level++;
			b_step++;
			b.setB_ref(p_no);
		}
		
		b.setNo(no);
		b.setB_step(b_step);
		b.setB_level(b_level);
		
		
		bs.insert(b);
		return mav;
	}
	
	@GetMapping("/board/list")
	public void list(Model model) {
		//model.addAttribute("list", bs.findAll());
		model.addAttribute("list", bs.selectAll());
	}
	
	@GetMapping("/board/detail/{no}")
	public ModelAndView detail(@PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/detail");
		System.out.println(no);
		mav.addObject("b", bs.findById(no));
		return mav;
	}
	
	
}

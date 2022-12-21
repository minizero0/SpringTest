package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	int pageSIZE = 5;
	int totalRecord = 0;
	int totalPage = 1;
	
	
	@Autowired
	private BoardService bs;
	
	
	@GetMapping("/board/list/{pageNUM}")
	public ModelAndView list(Model model, @PathVariable int pageNUM, HttpSession session) {
		//model.addAttribute("list", bs.findAll());
		ModelAndView mav = new ModelAndView("/board/list");
		totalRecord = bs.total();
		totalPage = (int)Math.ceil((double)totalRecord / pageSIZE);
		int start = (pageNUM-1)*pageSIZE+1;
		int end = start + pageSIZE-1;
		
		//model.addAttribute("id",session.getAttribute("id"));
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", bs.selectAll(start, end));
		return mav;
	}
	
	@GetMapping("/board/insert")
	public ModelAndView insertForm(HttpSession session, @RequestParam(value = "no", defaultValue="0") int no ,Model model) {
		ModelAndView mav = new ModelAndView();
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("error");
			mav.addObject("msg","로그인하세요");
		}
		System.out.println(no);
		if(no>0) {
			model.addAttribute("no", no);
		}else {
			model.addAttribute("no", bs.getNextNo());
		}
		return mav;
	}
	
	@PostMapping("/board/insert")
	public ModelAndView insertSubmit(Board b, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/board/list/1");
		String ip = request.getRemoteAddr();
		b.setIp(ip);
		
		String path = request.getServletContext().getRealPath("images");
		MultipartFile uploadFile = b.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		if(fname != null && !fname.equals("")) {
			try {
//				byte[] data = uploadFile.getBytes();
//				System.out.println("path:"+path);
//				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
//				fos.write(data);
//				fos.close();

				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
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
	
	
	
	@GetMapping("/board/detail/{no}")
	public ModelAndView detail(@PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/detail");
		ArrayList<String> imgList = new ArrayList<>();
		imgList.add(".jpg");
		imgList.add(".png");
		imgList.add(".jpeg");
		System.out.println(no);
		mav.addObject("b", bs.findById(no));
		mav.addObject("imgList", imgList);
		return mav;
	}
	
	@GetMapping("/board/delete/{no}")
	public ModelAndView deleteForm(@PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/delete");
		mav.addObject("no",no);
		return mav;
	}
	
	@PostMapping("/board/delete")
	public ModelAndView delete(int no,String pwd,HttpServletRequest request) {
		String fname = bs.findById(no).getFname();
		String path = request.getServletContext().getRealPath("images");
		ModelAndView mav = new ModelAndView("redirect:/board/list");
		if (bs.delete(no,pwd) > 0) {
			try {
				File file = new File(path + "/" + fname);
				file.delete();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}		
		return mav;
	}
	
	@GetMapping("/board/update/{no}")
	public ModelAndView updateForm(@PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/update");
		mav.addObject("b",bs.findById(no));
		return mav;
	}
	
	@PostMapping("/board/update")
	public ModelAndView delete(Board b,HttpServletRequest request) {
		System.out.println(b);
		String oldFname = bs.findById(b.getNo()).getFname();
		System.out.println("oldFname");
		String path = request.getServletContext().getRealPath("images");
		MultipartFile uploadFile = b.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		ModelAndView mav = new ModelAndView("redirect:/board/list");
		
		if(fname != null && !fname.equals("")) {
			b.setFname(fname);
		}else {
			fname = oldFname;
			b.setFname(fname);
		}
		
		
		
		if (bs.update(b) > 0 ) {
			try {
				File file = new File(path + "/" + oldFname);
				file.delete();
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}else {
			mav.setViewName("error");
			mav.addObject("msg", "수정오류 발생");
		}
		return mav;
	}
	
}

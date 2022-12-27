package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
import com.example.demo.dao.MemberDAO;
import com.example.demo.db.DBManager;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.service.BoardService;
import com.example.demo.vo.BoardVO;

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
	
	@Autowired
	private MemberDAO memberDAO;
	
	
	@GetMapping("/board/list/{pageNUM}/{id}")
	public ModelAndView list(Model model, @PathVariable int pageNUM, @PathVariable String id, HttpSession session) {
		//인증된(로그인한) 회원의 정보를 가져오기 위하여
		//시큐리티의 인증객체가 필요.
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		
		//이 인증객체를 통해서 인증된(로그인된) User객체를 받아 온다.
		User user = (User)authentication.getPrincipal();
		
		//이 인증된 User를 통해서 로그인된 회원의 아이디를 가져온다.
		String id2 = user.getUsername();
		//아이디 정보를 세션에 상태유지 한다.
		//만약, id뿐 아니라 로그인한 회원의 다른정보도 필요하다면 dao를 통해 회원 정보를 가져와서 상태유지
		
		session.setAttribute("id2", memberDAO.findById(id2).get());
		List<Board> list = null;
		//model.addAttribute("list", bs.findAll());
		ModelAndView mav = new ModelAndView("/board/list");
		System.out.println(id);
		totalRecord = bs.total();
		
		//model.addAttribute("id",session.getAttribute("id"));
		model.addAttribute("totalPage", totalPage);
		if(id!=null && !id.equals("all")) {
			totalRecord = bs.countById(id);
		}
		
		totalPage = (int)Math.ceil((double)totalRecord / pageSIZE);
		int start = (pageNUM-1)*pageSIZE+1;
		int end = start + pageSIZE-1;
		if(!id.equals("all")) {
			model.addAttribute("list", bs.selectAllById(start, end, id));
		}else {
			model.addAttribute("list", bs.selectAll(start, end));
		}
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("id", id);
		
		return mav;
	}
	
	@GetMapping("/board/insert")
	public ModelAndView insertForm(HttpSession session, @RequestParam(value = "no", defaultValue="0") int no ,Model model) {
		ModelAndView mav = new ModelAndView("/board/insert");
		String id = (String)session.getAttribute("id2");
		System.out.println("id:"+id);
//		if(id == null) {
//			mav.setViewName("error");
//			mav.addObject("msg","로그인하세요");
//		}
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
		System.out.println(b);
		ModelAndView mav = new ModelAndView("redirect:/board/list/1/all");
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
		ModelAndView mav = new ModelAndView("redirect:/board/list/1/all");
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
		ModelAndView mav = new ModelAndView("redirect:/board/list/1/all");
		
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
	
	
	@GetMapping("/board/findAll")
	public void findAll(Model model){
		model.addAttribute("list", DBManager.findAll());
	}
	
}

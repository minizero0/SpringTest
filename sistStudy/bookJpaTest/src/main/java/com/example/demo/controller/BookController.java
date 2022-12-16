package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BookDAO;
import com.example.demo.service.BookService;
import com.example.demo.vo.BookVO;

@Controller
public class BookController {
	@Autowired
	private BookService bs;
	
	@Autowired
	private BookDAO dao;
	
	@RequestMapping("/book/list")
	public void findAll(Model model,String sort_col, String column, String content, HttpServletRequest request, HttpSession session) {
		List<BookVO> list = null;
		
		if(request.getMethod().equals("GET")) {
			if(content == null || content.equals("") && session.getAttribute("content")!=null) {
				content = (String)session.getAttribute("content");
				column = (String)session.getAttribute("column");
			}
			if(sort_col.equals("bookname")) {
				list = dao.findAll(Sort.by(Sort.Direction.DESC, "bookname"));
				System.out.println(list);
			}
			if(sort_col.equals("bookid")) {
				list = dao.findAll(Sort.by("bookid"));
			}
			else {
				if(sort_col.equals("bookid") || sort_col.equals("price")) {
					int var = Integer.parseInt(content);
					//list = dao.sort_num(column,sort_col, var);
				}else {
					System.out.println(sort_col +"\n" + content);
					//list = dao.sort(column,sort_col, content);
					System.out.println(list);
				}
				
			}
			
		}else {
			System.out.println("컬럼:"+column+"\n컨텐트:"+content);
			if(column.equals("bookname"))
				list = bs.findByBookname(content);
			else if(column.equals("bookid")) {
				int bookid = Integer.parseInt(content);
				list = bs.findByBookid(bookid);
			}
			else if(column.equals("price")) {
				int price = Integer.parseInt(content);
				list = bs.findByPrice(price);
			}
			else if(column.equals("publisher")) {
				list = bs.findByPublisher(content);
			}
		}
		session.setAttribute("content", content);
		session.setAttribute("column", column);
		model.addAttribute("list",list);
	}
	@GetMapping("/book/insert")
	public void insert() {
	}
	@PostMapping("/book/save")
	public ModelAndView save(BookVO b) {
		ModelAndView mav = new ModelAndView("redirect:/book/list");
		bs.save(b);
		return mav;
	}
	@GetMapping("/book/update/{bookid}")
	public ModelAndView update(@PathVariable int bookid) {
		ModelAndView mav = new ModelAndView("/book/update");
		mav.addObject("b", bs.getOne(bookid));
		return mav;
	}
	@GetMapping("/book/delete/{bookid}")
	public ModelAndView delete(@PathVariable int bookid) {
		ModelAndView mav = new ModelAndView("redirect:/book/list");
		bs.delete(bookid);
		return mav;
	}
	
	
}

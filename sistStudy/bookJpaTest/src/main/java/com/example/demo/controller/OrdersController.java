package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.View_ListOrdersDAO;
import com.example.demo.dao.View_ListOrdersDAO2;
import com.example.demo.service.BookService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrdersService;
import com.example.demo.vo.OrdersVO;
import com.example.demo.vo.View_ListOrders;

import lombok.Setter;

@Controller
@Setter
public class OrdersController {
	
	@Autowired
	private OrdersService os;
	@Autowired
	private BookService bs;
	@Autowired
	private CustomerService cs;
	
	@Autowired
	private View_ListOrdersDAO view_ListOrdersDAO;
	
	@Autowired
	private View_ListOrdersDAO2 view_ListOrdersDAO2;
	
	@GetMapping("/orders/list")
	public void list(Model model) {
		System.out.println(os.findAll());
		model.addAttribute("list", os.findAll());
	}
	
	@RequestMapping("/orders/list2")
	public void list2(Model model, String keyword, String column, HttpServletRequest request, HttpSession session) {
		List<View_ListOrders> list = null;
		
		if((keyword == null || keyword.equals("")) && session.getAttribute("keyword") != null) {
			keyword = (String)session.getAttribute("keyword");
		}
		
		if(request.getMethod().equals("GET")) {
			System.out.println(column);
			if(column==null) {
				list = view_ListOrdersDAO.findAll();
				//model.addAttribute("list", view_ListOrdersDAO.findAll());
			}else {
				System.out.println("column:"+column+"\nkeyword:"+keyword);
				list = view_ListOrdersDAO.sort(column, keyword);
				System.out.println(list);
				//model.addAttribute("list", view_ListOrdersDAO.sort(column));
			}
		}else {
			switch(column) {
			case "name":list =  view_ListOrdersDAO.findByNameContaining(keyword);break;
				//model.addAttribute("list", view_ListOrdersDAO.findByNameContaining(keyword));break;
			case "bookname":list =  view_ListOrdersDAO.findByBooknameContaining(keyword);break;
			//case "bookname": model.addAttribute("list", view_ListOrdersDAO.findByBooknameContaining(keyword));break;
			case "orderid":list =  view_ListOrdersDAO.findByOrderid(Integer.parseInt(keyword));break;
			//case "orderid" : model.addAttribute("list", view_ListOrdersDAO.findByOrderid(Integer.parseInt(keyword)));break;
			}
		}
		model.addAttribute("list",list);
		session.setAttribute("keyword", keyword);
	}
	
	@GetMapping("/orders/insert")
	public void insertForm(Model model) {
		System.out.println("ready");
		model.addAttribute("orderid", os.getNextNo());
		model.addAttribute("c_list", cs.findAll());
		model.addAttribute("b_list", bs.findAll());
	}
	
	@PostMapping("/orders/insert")
	public ModelAndView insertSubmit(OrdersVO o) {
		System.out.println(o);
		os.insert(o);
		ModelAndView mav = new ModelAndView("redirect:/orders/list");
		return mav;
	}
	
	@GetMapping("/orders/list3")
	public void list3(Model model) {
		model.addAttribute("list", view_ListOrdersDAO2.findAll());
	}
	
}

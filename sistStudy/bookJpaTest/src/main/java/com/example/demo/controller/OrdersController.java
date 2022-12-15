package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.View_ListOrdersDAO;
import com.example.demo.service.BookService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrdersService;
import com.example.demo.vo.OrdersVO;

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
	
	@GetMapping("/orders/list")
	public void list(Model model) {
		System.out.println(os.findAll());
		model.addAttribute("list", os.findAll());
	}
	
	@GetMapping("/orders/list2")
	public void list2(Model model) {
		model.addAttribute("list", view_ListOrdersDAO.findAll());
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
	
}

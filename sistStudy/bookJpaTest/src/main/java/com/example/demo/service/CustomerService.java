package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.vo.CustomerVO;

import lombok.Setter;

@Service
@Setter
public class CustomerService {
	
	@Autowired
	private CustomerDAO dao;
	
	public List<CustomerVO> findAll() {
		return dao.findAll();
	}
	
	public 

}

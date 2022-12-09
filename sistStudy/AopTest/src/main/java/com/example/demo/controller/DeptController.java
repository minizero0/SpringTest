package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DeptDAO;
import com.example.demo.vo.DeptVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@RestController
@Setter
public class DeptController {

	@Autowired
	private DeptDAO dao;
	
	@RequestMapping("/listDept")	
	public List<DeptVO> listDept(){
		return dao.findAll();
	}
	
	
	@RequestMapping("/insertDept")
	public String insert(DeptVO d) {
		int re= dao.insert(d);
		return ""+re;
	}
	
	@RequestMapping("/updateDept")	
	public String update(DeptVO d) {
		int re= dao.update(d);
		return ""+re;
	}
	
	@RequestMapping("/deleteDept")
	public String delete(int dno) {
		int re = dao.delete(dno);
		return ""+re;
	}
	
}
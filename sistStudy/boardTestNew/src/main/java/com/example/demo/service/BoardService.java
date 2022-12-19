package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.entity.Board;

import lombok.Setter;

@Service
@Setter
public class BoardService {

	@Autowired
	private BoardDAO dao;
	
//	public void save(Board b) {
//		dao.save(b);
//	}
	
	public void insert(Board b) {
		dao.insert(b);
	}
	
	public int getNextNo() {
		return dao.getNextNo();
	}
	
}

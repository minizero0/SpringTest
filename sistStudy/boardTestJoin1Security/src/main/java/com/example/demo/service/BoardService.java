package com.example.demo.service;

import java.util.List;

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
	
	public List<Board> findAll(){
		return dao.findAll();
	}
	
	public Board findById(int no) {
		return dao.findById(no).get();
	}
	
	public int findByNo(int no) {
		try{
			return dao.findByNo(no);
		}catch (Exception e) {
			return -1;
		}
	}

	public void updateStep(int b_ref, int b_step) {
		dao.updateStep(b_ref, b_step);
		System.out.println("ref"+b_ref +"\nb_step"+b_step);
		
	}
	
	public List<Board> selectAll(int start, int end){
		return dao.selectAll(start, end);
	}
	
	public int delete(int no, String pwd) {
		int re = -1;
		re = dao.deleteBoard(no, pwd);
		return re;
	}
	
	public int update(Board b) {
		int re = -1;
		re = dao.update(b);
		return re;
	}
	
	public int total() {
		return dao.total();
	}

	public List<Board> selectById(String id) {
		return dao.selectById(id);
		
	}

	public List<Board> selectAllById(int start, int end, String id) {
		// TODO Auto-generated method stub
		return dao.selectAllById(start,end,id);
	}
	
	public int countById(String id) {
		return dao.countById(id);
	}
	
}

package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

@Repository
public class MemberDAO {

	public MemberVO findById(int no) {
		return DBManager.findById(no);
	}

	public List<MemberVO> findAll() {
		// TODO Auto-generated method stub
		return DBManager.findAll();
	}
	
}

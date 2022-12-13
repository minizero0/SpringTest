package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.DeptVO;

@Repository
public class DeptDAO {
	
	
	public List<DeptVO> findAll(){
		return DBManager.findAll();
	}

	public DeptVO findByNo(int dno) {
		// TODO Auto-generated method stub
		return DBManager.findByNo(dno);
	}

	public int delete(int dno) {
		// TODO Auto-generated method stub
		return DBManager.delete(dno);
		
	}
}

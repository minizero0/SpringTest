package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.EmpVO;

@Repository
public class EmpDAO {

	public List<EmpVO> findAll() {
		// TODO Auto-generated method stub
		return DBManager.findAll();
	}

}

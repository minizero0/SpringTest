package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.DeptVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Repository
public class LogDAO {

	public int insert(HashMap<String, Object> map) {
		return DBManager.insertLog(map);
	}

	public int nextNo() {
		return DBManager.nextNo();
	}
	
	
}
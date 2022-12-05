package com.example.demo.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

@Repository
public class MemberDAO {

	public int signUp(MemberVO m) {
		// TODO Auto-generated method stub
		return DBManager.signUp(m);
	}

	public int logIn(HashMap<String, Object> map) {
		return DBManager.logIn(map);
		
	}
	
}
